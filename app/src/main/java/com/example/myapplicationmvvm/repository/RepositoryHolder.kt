package com.example.myapplicationmvvm.repository

import com.example.myapplicationmvvm.service.Endpoint

// This object is used to hold the instance of the CityRepository
// It is used to initialize the repository in the Activity
object RepositoryHolder {
    val cityRepository by lazy { CityRepository(Endpoint.createInstance()) }
}

