package com.uk.androidrecruitmentapp.api_data.page_data_sources.next_page_episodes

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.uk.androidrecruitmentapp.api.ApiService
import com.uk.androidrecruitmentapp.data.episode.Result
import com.uk.androidrecruitmentapp.executor.AppExecutors

class PagesEpisodeDataSourceFactory(private val appExecutors: AppExecutors,
                                    private val apiService: ApiService): DataSource.Factory<Int, Result>() {

    val sourceLiveData = MutableLiveData<PagesEpisodeDataSource>()

    override fun create(): DataSource<Int, Result> {
        val source = PagesEpisodeDataSource(appExecutors,apiService)
        sourceLiveData.postValue(source)
        return source
    }
}