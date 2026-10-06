package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    // Cambiar a "posts" y devolver una List<DataPost>
    @GET(value = "posts")
    suspend fun getPosts(): List<DataPost>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        val instance: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}