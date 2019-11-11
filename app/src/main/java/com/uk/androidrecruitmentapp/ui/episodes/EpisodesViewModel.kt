package com.uk.androidrecruitmentapp.ui.episodes

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.uk.androidrecruitmentapp.api.Resource
import com.uk.androidrecruitmentapp.api_data.MainRepository
import com.uk.androidrecruitmentapp.data.episode.Result
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    private val _repoResult  = repository.loadEpisodes()

    val result: LiveData<PagedList<Result>> = Transformations.switchMap(_repoResult) {
        it.pagedList
    }

    val networkState: LiveData<Resource> = Transformations.switchMap(_repoResult) {
        it.networkState
    }
}