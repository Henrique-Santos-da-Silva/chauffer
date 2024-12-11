package com.example.chauffeur.util


sealed class Resource<out T>(val code: Int? = null, val data: T? = null, val message: String? = null) {
    class Success<out T>(code: Int, data: T): Resource<T>(code, data)
    class Error<out T>(code: Int?, message: String): Resource<T>(code = code, message = message)
    class Loading<out T> : Resource<T>()
}