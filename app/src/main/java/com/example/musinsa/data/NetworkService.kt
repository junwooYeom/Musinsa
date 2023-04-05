package com.example.musinsa.data

import retrofit2.http.GET

interface NetworkService {

    @GET("/interview/list.json")
    suspend fun getItemList(): ContentResponse
}