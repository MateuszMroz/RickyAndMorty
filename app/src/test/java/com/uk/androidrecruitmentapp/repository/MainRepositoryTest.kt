package com.uk.androidrecruitmentapp.repository

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.lifecycle.MutableLiveData
import com.uk.androidrecruitmentapp.api.ApiService
import com.uk.androidrecruitmentapp.api_data.MainRepository
import com.uk.androidrecruitmentapp.data.episode.Result
import com.uk.androidrecruitmentapp.util.AppExecutorsForTest
import com.uk.androidrecruitmentapp.util.Listing
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.MockitoAnnotations
import org.junit.Before

@RunWith(JUnit4::class)
class MainRepositoryTest {
    private val apiService = mock(ApiService::class.java)
    private val repository = MainRepository(AppExecutorsForTest(), apiService)

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadEpisodes() {
        repository.loadEpisodes()
        val data: MutableLiveData<Listing<Result>> = MutableLiveData()
        `when`(repository.loadEpisodes()).thenReturn(data)

        verify(repository).loadEpisodes()
    }
}