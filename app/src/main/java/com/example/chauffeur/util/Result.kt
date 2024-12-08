package com.example.chauffeur.util


sealed class Result<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T): Result<T>(data)
    class Error<out T>(message: String, data: T? = null): Result<T>(data, message)
    class Loading<out T> : Result<T>()
}