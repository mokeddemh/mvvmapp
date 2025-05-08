package com.example.myapplicationmvvm.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplicationmvvm.ERROR_MESSAGE
import com.example.myapplicationmvvm.entity.City
import com.example.myapplicationmvvm.state.UiState
import com.example.myapplicationmvvm.util.makeToast
import com.example.myapplicationmvvm.viewmodel.CityModel

@Composable
fun DisplayDetails(cityModel: CityModel = hiltViewModel(), id: Int) {
    val context = LocalContext.current
    val state  by cityModel.cityState.collectAsState()
    LaunchedEffect(true) {
        cityModel.getCity(id)
    }

    when (state) {
        is UiState.Loading -> {
            LoadingView()
        }
        is UiState.Success -> {
            val successState = state as UiState.Success<City>
            CityDetails(successState.data)
        }
        is UiState.Error -> {
            ERROR_MESSAGE.makeToast(context)
        }
    }
}

@Composable
fun CityDetails(city: City) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = city.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        CachedImage(city.imageUrl)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = city.description ?: "", fontSize = 16.sp, fontWeight = FontWeight.W300)

    }

}


