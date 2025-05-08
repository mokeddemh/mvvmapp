package com.example.myapplicationmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationmvvm.navigation.Destination
import com.example.myapplicationmvvm.screen.DisplayCities
import com.example.myapplicationmvvm.screen.DisplayDetails
import com.example.myapplicationmvvm.ui.theme.MyApplicationMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationMVVMTheme  {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    MainApp(navController,Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainApp(navController: NavHostController,modifier: Modifier){
    NavHost (navController = navController, startDestination = Destination.ListScreen.route, modifier = modifier) {
        composable(Destination.ListScreen.route) {
            DisplayCities(navController = navController)
        }

        composable(Destination.DetailScreen.route) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")?.toInt()
            DisplayDetails(id = id!!)
        }
    }
}



