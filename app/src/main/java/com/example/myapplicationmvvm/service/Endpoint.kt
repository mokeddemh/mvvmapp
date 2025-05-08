package com.example.myapplicationmvvm.service

import com.example.myapplicationmvvm.entity.City
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

    @GET("getlistcities")
    suspend fun getCities(): List<City>

    @GET("getcity/{id}")
    suspend fun getCity(@Path("id") id: Int): City

}