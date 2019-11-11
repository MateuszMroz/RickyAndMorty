package com.uk.androidrecruitmentapp.api_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.uk.androidrecruitmentapp.api.ApiService
import com.uk.androidrecruitmentapp.api_data.page_data_sources.next_page_characters.PagesCharactersDataSourceFactory
import com.uk.androidrecruitmentapp.api_data.page_data_sources.next_page_episodes.PagesEpisodeDataSourceFactory
import com.uk.androidrecruitmentapp.api_data.page_data_sources.next_page_locations.PagesLocationsDataSourceFactory
import com.uk.androidrecruitmentapp.executor.AppExecutors
import com.uk.androidrecruitmentapp.util.Listing
import com.uk.androidrecruitmentapp.util.PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val apiService: ApiService) {

    private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()


    fun loadEpisodes(): LiveData<Listing<com.uk.androidrecruitmentapp.data.episode.Result>> {
        val sourceFactory = PagesEpisodeDataSourceFactory(appExecutors, apiService)

        val data: MutableLiveData<Listing<com.uk.androidrecruitmentapp.data.episode.Result>> = MutableLiveData()
        data.value = Listing(pagedList = LivePagedListBuilder(sourceFactory, config).build(),
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                    it.networkState
                })

        return data
    }

    fun loadCharacters(): LiveData<Listing<com.uk.androidrecruitmentapp.data.characters.Result>>  {
        val sourceFactory = PagesCharactersDataSourceFactory(appExecutors, apiService)

        val data: MutableLiveData<Listing<com.uk.androidrecruitmentapp.data.characters.Result>> = MutableLiveData()
        data.value = Listing(pagedList = LivePagedListBuilder(sourceFactory, config).build(),
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                    it.networkState
                })

        return data
    }

    fun loadLocations(): LiveData<Listing<com.uk.androidrecruitmentapp.data.locations.Result>>  {
        val sourceFactory = PagesLocationsDataSourceFactory(appExecutors, apiService)

        val data: MutableLiveData<Listing<com.uk.androidrecruitmentapp.data.locations.Result>> = MutableLiveData()
        data.value = Listing(pagedList = LivePagedListBuilder(sourceFactory, config).build(),
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                    it.networkState
                })

        return data
    }
}