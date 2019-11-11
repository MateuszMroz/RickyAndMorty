package com.uk.androidrecruitmentapp.api

import com.uk.androidrecruitmentapp.util.EMPTY_RESPONSE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


class RequestCallback<RequestType>(private val callback:Callback<RequestType>): Callback<RequestType> {

    override fun onResponse(call: Call<RequestType>, response: Response<RequestType>) {
        if(response.isSuccessful) {
        val body = response.body()
            if (body == null || response.code() == EMPTY_RESPONSE) {
                callback.onFailure("Empty response.")
            } else {
               callback.onSuccess(body)
            }
        } else {
            callback.onFailure(response.errorBody()?.toString() ?: "unknown message")
        }
    }

    override fun onFailure(call: Call<RequestType>, t: Throwable) {
        when (t) {
            is SocketTimeoutException ->  callback.onFailure("The server, while acting as a gateway or proxy, did not receive a timely response from an upstream server it needed to access in order to complete the request")
            is IOException -> callback.onFailure("No network connection!")
            else ->  callback.onFailure(t.message ?: "unknown error")
        }
    }


    interface Callback<RequestType> {
        fun onSuccess(response: RequestType)
        fun onFailure(message: String)
    }
}
