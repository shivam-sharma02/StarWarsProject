package com.example.starwars

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface StarAPI {

    @GET("people/")
    suspend fun getPeople(): Response<StarWarPeople>

    companion object {
        val instance by lazy {
            Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StarAPI::class.java)
        }
    }


}