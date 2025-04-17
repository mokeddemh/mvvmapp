package com.example.myapplicationmvvm.navigation

sealed class Destination(val route:String) {
    object ListScreen : Destination("cityList")
    object DetailScreen : Destination("cityDetail/{id}") {
        fun createRoute(id: Int) = "cityDetail/$id"
    }
}