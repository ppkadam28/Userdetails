package com.simplyfleet.assignment.network

import com.simplyfleet.assignment.ListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @POST("apikey=563e10ff&s=2017&page=1")
    suspend fun getListDetails(): ListResponse



}