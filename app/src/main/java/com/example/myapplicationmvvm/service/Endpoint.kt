package com.example.myapplicationmvvm.service

import com.example.myapplicationmvvm.BASE_URL
import com.example.myapplicationmvvm.entity.City
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

    @GET("getlistcities")
    suspend fun getCities(): List<City>

    @GET("getcity/{id}")
    suspend fun getCity(@Path("id") id: Int): City


    companion object {
        private var INSTANCE: Endpoint? = null
        fun createInstance(): Endpoint {
            if(INSTANCE ==null) {
                INSTANCE = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().
                    create(Endpoint::class.java)
            }
            return INSTANCE!!
        }
    }

}