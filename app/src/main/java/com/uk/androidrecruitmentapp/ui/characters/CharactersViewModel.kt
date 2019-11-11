package com.uk.androidrecruitmentapp.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.uk.androidrecruitmentapp.api.Resource
import com.uk.androidrecruitmentapp.api_data.MainRepository
import com.uk.androidrecruitmentapp.data.characters.Result
import javax.inject.Inject


class CharactersViewModel @Inject constructor(repository: MainRepository): ViewModel() {
    private val _repoResult  = repository.loadCharacters()

    val result: LiveData<PagedList<Result>> = Transformations.switchMap(_repoResult) {
        it.pagedList
    }

    val networkState: LiveData<Resource> = Transformations.switchMap(_repoResult) {
        it.networkState
    }
}