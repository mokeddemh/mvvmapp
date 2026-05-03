package com.example.myapplicationmvvm.entity


data class City (
    val id: Int,
    val name: String,
    val imageUrl:String,
    val description:String? = null
)