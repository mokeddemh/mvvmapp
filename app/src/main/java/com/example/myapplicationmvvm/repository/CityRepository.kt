package com.example.myapplicationmvvm.repository

import com.example.myapplicationmvvm.entity.City
import com.example.myapplicationmvvm.ERROR_MESSAGE
import com.example.myapplicationmvvm.service.Endpoint
import com.example.myapplicationmvvm.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CityRepository(private val endpoint: Endpoint) {

    fun getCities(): Flow<UiState<List<City>>> = flow {
        emit(UiState.Loading)
        try {
            val cities = endpoint.getCities()
            emit(UiState.Success(cities))
        } catch (e: Exception) {
            emit(UiState.Error(ERROR_MESSAGE))
        }

    }



    fun getCity(id:Int) = flow {
        emit(UiState.Loading)
        try {
            val city = endpoint.getCity(id)
            emit(UiState.Success(city))
        }
        catch (e:Exception) {
            emit(UiState.Error(ERROR_MESSAGE))
        }
}

}

