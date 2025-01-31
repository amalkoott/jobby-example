package ru.amalkoott.network

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{
    @GET(BuildConfig.LOAD_DATA)
    suspend fun get(): Response<JsonObject?>
}