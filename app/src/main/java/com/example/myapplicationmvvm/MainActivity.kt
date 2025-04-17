package com.example.myapplicationmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationmvvm.navigation.Destination
import com.example.myapplicationmvvm.repository.RepositoryHolder
import com.example.myapplicationmvvm.screen.DisplayCities
import com.example.myapplicationmvvm.screen.DisplayDetails
import com.example.myapplicationmvvm.ui.theme.MyApplicationMVVMTheme
import com.example.myapplicationmvvm.viewmodel.CityModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationMVVMTheme  {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    MainApp(navController)
                }
            }
        }
    }
}

@Composable
fun MainApp(navController: NavHostController){
    val cityModel = CityModel(RepositoryHolder.cityRepository)
    NavHost (navController = navController, startDestination = Destination.ListScreen.route) {
        composable(Destination.ListScreen.route) {
            DisplayCities(cityModel,navController)
        }

        composable(Destination.DetailScreen.route) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")?.toInt()
            DisplayDetails(cityModel,id!!)
        }
    }
}



