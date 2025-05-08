package com.example.myapplicationmvvm

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplicationmvvm.entity.City
import com.example.myapplicationmvvm.service.Endpoint
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(AndroidJUnit4::class)
class UnitTest {

    private lateinit var endpoint: Endpoint
    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        // Create an Instance of Mock Server
        server = MockWebServer()
        // Create Endpoint with Mock Server
        endpoint = Retrofit.Builder().baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(Endpoint::class.java)
    }

    @Test
    fun testUnitGetCities() {
        val expectedCities  = listOf(
            City(1, "Barcelone", "http://197.140.142.82:8082/images/main/barcelona.png"),
            City(2, "Istanbul", "http://197.140.142.82:8082/images/main/istanbul.png"),
            City(3, "Londres", "http://197.140.142.82:8082/images/main/london.png"),
            City(4, "Paris", "http://197.140.142.82:8082/images/main/paris.png"),
            City(5, "Rome", "http://197.140.142.82:8082/images/main/roma.png")
        )
        val body= "[{\"id\":1,\"name\":\"Barcelone\",\"imageurl\":\"http://197.140.142.82:8082/images/main/barcelona.png\"},{\"id\":2,\"name\":\"Istanbul\",\"imageurl\":\"http://197.140.142.82:8082/images/main/istanbul.png\"},{\"id\":3,\"name\":\"Londres\",\"imageurl\":\"http://197.140.142.82:8082/images/main/london.png\"},{\"id\":4,\"name\":\"Paris\",\"imageurl\":\"http://197.140.142.82:8082/images/main/paris.png\"},{\"id\":5,\"name\":\"Rome\",\"imageurl\":\"http://197.140.142.82:8082/images/main/roma.png\"}]"
        // Mock the server response
        server.enqueue(MockResponse().setResponseCode(200).setBody(body) )
        runBlocking {
            val list = endpoint.getCities()
            assertArrayEquals(expectedCities.toTypedArray(),list?.toTypedArray())

        }

    }


}