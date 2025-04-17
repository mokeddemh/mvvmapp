package com.example.myapplicationmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationmvvm.entity.City
import com.example.myapplicationmvvm.repository.CityRepository
import com.example.myapplicationmvvm.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityModel(private val repository: CityRepository):ViewModel() {

    private val _citiesState = MutableStateFlow<UiState<List<City>>>(UiState.Loading)
    // read only
    val citiesState: StateFlow<UiState<List<City>>> = _citiesState.asStateFlow()

    private val _cityState = MutableStateFlow<UiState<City>>(UiState.Loading)
    // read only
    val cityState: StateFlow<UiState<City>> = _cityState

    init {
        getCities()
    }

    private fun getCities() {
        viewModelScope.launch {
            repository.getCities()
                .collect { state ->
                    _citiesState.value = state
                }
        }
        }



    fun getCity(id: Int) {
        viewModelScope.launch {
            repository.getCity(id)
                .collect { state ->
                    _cityState.value = state
                }
        }
    }

}




