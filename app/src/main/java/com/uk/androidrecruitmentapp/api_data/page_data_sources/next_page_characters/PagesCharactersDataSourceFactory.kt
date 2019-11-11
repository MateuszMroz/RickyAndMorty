package com.uk.androidrecruitmentapp.api_data.page_data_sources.next_page_characters

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.uk.androidrecruitmentapp.api.ApiService
import com.uk.androidrecruitmentapp.data.characters.Result
import com.uk.androidrecruitmentapp.executor.AppExecutors


class PagesCharactersDataSourceFactory(private val appExecutors: AppExecutors,
                                       private val apiService: ApiService): DataSource.Factory<Int, Result>() {

    val sourceLiveData = MutableLiveData<PagesCharactersDataSource>()

    override fun create(): DataSource<Int, Result> {
        val source = PagesCharactersDataSource(appExecutors, apiService)
        sourceLiveData.postValue(source)
        return source
    }
}