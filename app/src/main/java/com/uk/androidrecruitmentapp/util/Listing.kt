package com.uk.androidrecruitmentapp.util

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.uk.androidrecruitmentapp.api.Resource

data class Listing<T>(val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<Resource>): LiveData<T>()