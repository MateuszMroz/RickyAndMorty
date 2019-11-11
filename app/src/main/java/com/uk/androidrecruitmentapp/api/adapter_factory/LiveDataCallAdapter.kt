package com.uk.androidrecruitmentapp.api.adapter_factory

import androidx.lifecycle.LiveData
import com.uk.androidrecruitmentapp.api.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean


class LiveDataCallAdapter<T>(private val responseType: Type): CallAdapter<T, LiveData<ApiResponse<T>>> {

    override fun adapt(call: Call<T>): LiveData<ApiResponse<T>> {
        return object : LiveData<ApiResponse<T>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if(started.compareAndSet(false, true)){
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            postValue(ApiResponse.create(t))
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            postValue(ApiResponse.create(response))
                        }

                    })
                }
            }
        }
    }

    override fun responseType(): Type = responseType

}