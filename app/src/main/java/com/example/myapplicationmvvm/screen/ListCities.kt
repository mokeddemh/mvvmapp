package com.example.myapplicationmvvm.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplicationmvvm.entity.City
import com.example.myapplicationmvvm.ERROR_MESSAGE
import com.example.myapplicationmvvm.navigation.Destination
import com.example.myapplicationmvvm.state.UiState
import com.example.myapplicationmvvm.util.makeToast
import com.example.myapplicationmvvm.viewmodel.CityModel


@Composable
fun DisplayCities(cityModel: CityModel, navController: NavHostController) {
    val state  by cityModel.citiesState.collectAsState()
    val context = LocalContext.current
    when (state) {
        is UiState.Loading -> {
            LoadingView()
        }
        is UiState.Success -> {
            val successState = state as UiState.Success<List<City>>
            CitiesList(successState.data, navController)
        }
        is UiState.Error -> {
            ERROR_MESSAGE.makeToast(context)
        }
    }
    }

@Composable
fun CitiesList(data: List<City>, navController: NavHostController) {
    LazyColumn {
        items(data) {
            Card(
                modifier = Modifier.clickable {
                    navController.navigate(Destination.DetailScreen.createRoute(it.id))
                }.padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF7F2F9)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = it.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    AsyncImage(
                        model = it.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}





