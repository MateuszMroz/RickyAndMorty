package com.uk.androidrecruitmentapp.api_data.page_data_sources.next_page_episodes

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.uk.androidrecruitmentapp.api.*
import com.uk.androidrecruitmentapp.data.episode.Episodes
import com.uk.androidrecruitmentapp.data.episode.Result
import com.uk.androidrecruitmentapp.executor.AppExecutors


class PagesEpisodeDataSource(private val appExecutors: AppExecutors,
                             private val apiService: ApiService) : PageKeyedDataSource<Int, Result>() {

    var PAGE_NUMBER: Int = 1
    val networkState = MutableLiveData<Resource>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
        networkState.postValue(Resource.loading(false))
        appExecutors.networkIO().execute {
            val callback: RequestCallback<Episodes> = RequestCallback<Episodes>(object : RequestCallback.Callback<Episodes> {
                override fun onSuccess(response: Episodes) {
                    if (!response.info?.next.isNullOrEmpty()) {
                        callback.onResult(response.results, null, PAGE_NUMBER)
                    }
                    networkState.postValue(Resource.success(false))
                }

                override fun onFailure(message: String) {
                    networkState.postValue(Resource.error(message, false))
                }

            })

            apiService.getEpisode(PAGE_NUMBER).enqueue(callback)
        }
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        networkState.postValue(Resource.loading(true))
        appExecutors.networkIO().execute {
            val callback: RequestCallback<Episodes> = RequestCallback<Episodes>(object : RequestCallback.Callback<Episodes> {
                override fun onSuccess(response: Episodes) {
                    if (!response.info?.next.isNullOrEmpty()) {
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

            apiService.getEpisode(params.key + 1).enqueue(callback)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

    }
}