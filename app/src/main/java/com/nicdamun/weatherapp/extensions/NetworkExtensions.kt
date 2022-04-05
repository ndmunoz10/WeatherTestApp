package com.nicdamun.weatherapp.extensions

import retrofit2.HttpException
import retrofit2.Response

suspend inline fun <T: Any> safeAPICall(crossinline call: suspend () -> Response<T>): Result<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (t: Throwable) {
        return Result.failure(t)
    }
    return if (!response.isSuccessful) {
        Result.failure(HttpException(response))
    } else {
        if (response.body() == null) {
            Result.failure(NullPointerException("Response body cannot be null"))
        } else {
            Result.success(response.body()!!)
        }
    }
}