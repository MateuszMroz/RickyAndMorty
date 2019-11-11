package com.uk.androidrecruitmentapp.api_data.page_data_sources.next_page_locations

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.uk.androidrecruitmentapp.api.ApiService
import com.uk.androidrecruitmentapp.data.locations.Result
import com.uk.androidrecruitmentapp.executor.AppExecutors


class PagesLocationsDataSourceFactory(private val appExecutors: AppExecutors,
                                      private val apiService: ApiService): DataSource.Factory<Int, Result>() {

    val sourceLiveData = MutableLiveData<PagesLocationsDataSource>()

    override fun create(): DataSource<Int, Result> {
        val source = PagesLocationsDataSource(appExecutors,apiService)
        sourceLiveData.postValue(source)
        return source
    }
}