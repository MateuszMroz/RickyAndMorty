package com.uk.androidrecruitmentapp.api

import com.uk.androidrecruitmentapp.util.Status


data class Resource(val status: Status, val isLoadNextPage: Boolean, val message: String?) {

    companion object {
        fun success(isLoadNextPage: Boolean): Resource {
            return Resource(Status.SUCCESS, isLoadNextPage, null)
        }

        fun error(message: String?, isLoadNextPage: Boolean): Resource {
            return Resource(Status.ERROR, isLoadNextPage, message)
        }

        fun loading(isLoadNextPage: Boolean): Resource {
            return Resource(Status.LOADING, isLoadNextPage, null)
        }
    }
}