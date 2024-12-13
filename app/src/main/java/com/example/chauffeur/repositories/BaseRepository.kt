package com.example.chauffeur.repositories

import android.util.Log
import com.example.chauffeur.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    Resource.Success(code = response.code(), data = response.body()!!)
                } else {
                    Resource.Error(code = response.code(), message = response.errorBody()?.string() ?: "Ops! Ocorreu um erro inesperado")
                }

            } catch (e: HttpException) {
                Resource.Error(code = e.code(), message = e.message ?: "Ops! Ocorreu um erro inesperado")
            } catch (e: IOException) {
                Resource.Error(null, "Por favor. Verifique sua conexão com a internet")
            } catch (e: Exception) {
                Log.e("erru", "safeApiCall: ${e.message}", )
                Resource.Error(null, message = "Ops! Ocorreu um erro inesperado")
            }
        }
    }
}