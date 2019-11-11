package com.uk.androidrecruitmentapp.api_data.page_data_sources.next_page_locations

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.uk.androidrecruitmentapp.api.ApiService
import com.uk.androidrecruitmentapp.api.RequestCallback
import com.uk.androidrecruitmentapp.api.Resource
import com.uk.androidrecruitmentapp.data.locations.Locations
import com.uk.androidrecruitmentapp.data.locations.Result
import com.uk.androidrecruitmentapp.executor.AppExecutors


class PagesLocationsDataSource (private val appExecutors: AppExecutors,
                                private val apiService: ApiService) : PageKeyedDataSource<Int, Result>() {

    var PAGE_NUMBER: Int = 1
    val networkState = MutableLiveData<Resource>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
        networkState.postValue(Resource.loading(false))
        appExecutors.networkIO().execute {
            val callback: RequestCallback<Locations> = RequestCallback<Locations>(object : RequestCallback.Callback<Locations> {
                override fun onSuccess(response: Locations) {
                    if (response.info.next.isNotEmpty()) {
                        callback.onResult(response.results, null, PAGE_NUMBER)
                    }
                    networkState.postValue(Resource.success(false))
                }

                override fun onFailure(message: String) {
                    networkState.postValue(Resource.error(message, false))
                }

            })

            apiService.getLocations(PAGE_NUMBER).enqueue(callback)
        }
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        networkState.postValue(Resource.loading(true))
        appExecutors.networkIO().execute {
            val callback: RequestCallback<Locations> = RequestCallback<Locations>(object : RequestCallback.Callback<Locations> {
                override fun onSuccess(response: Locations) {
                    if (response.info.next.isNotEmpty()) {
                        callback.onResult(response.results, params.key + 1)
                    } else {
                        callback.onResult(response.results, null)
                    }

                    networkState.postValue(Resource.success(true))
                }

                override fun onFailure(message: String) {
                    networkState.postValue(Resource.error(message, true))
                }

            })

            apiService.getLocations(params.key + 1).enqueue(callback)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

    }
}