package com.uk.androidrecruitmentapp.util

import com.uk.androidrecruitmentapp.executor.AppExecutors
import java.util.concurrent.Executor


class AppExecutorsForTest: AppExecutors(thread, thread) {
    companion object {
        private val thread = Executor{it.run()}
    }
}