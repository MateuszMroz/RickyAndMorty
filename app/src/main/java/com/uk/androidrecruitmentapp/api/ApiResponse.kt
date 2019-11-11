package com.uk.androidrecruitmentapp.api

import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


sealed class ApiResponse<T> {
   companion object {
       private const val emptyResponse = 204

       fun<T> create(error: Throwable): ApiErrorResponse<T> {
           return when (error) {
               is SocketTimeoutException -> ApiErrorResponse("Upłynął limit czasu połączenia.")
               is IOException -> ApiErrorResponse("Brak sieci!")
               else -> ApiErrorResponse(error.message ?: "unknown error")
           }
       }

       fun<T> create(response: Response<T>): ApiResponse<T> {
           return if(response.isSuccessful) {
               val body = response.body()
               if (body == null || response.code() == emptyResponse) {
                   ApiEmptyResponse()
               } else {
                   ApiSuccessResponse(body = body)
               }
           } else {
               ApiErrorResponse(response.errorBody()?.toString() ?: "unknown message")
           }
       }
   }
}

class ApiEmptyResponse<T>: ApiResponse<T>()
class ApiErrorResponse<T>(val error: String): ApiResponse<T>()
class ApiSuccessResponse<T>(val body: T): ApiResponse<T>()