package com.example.myapplicationmvvm.service

import com.example.myapplicationmvvm.entity.City
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

    @GET("api/cities")
    suspend fun getCities(): List<City>

    @GET("api/city/{id}")
    suspend fun getCity(@Path("id") id: Int): City

}