package com.uk.androidrecruitmentapp.api

import com.uk.androidrecruitmentapp.data.characters.Characters
import com.uk.androidrecruitmentapp.data.episode.Episodes
import com.uk.androidrecruitmentapp.data.locations.Locations
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("episode")
    fun getEpisode(@Query("page") page:Int): Call<Episodes>

    @GET("character")
    fun getCharacter(@Query("page") page:Int): Call<Characters>

    @GET("location")
    fun getLocations(@Query("page") page:Int): Call<Locations>
}