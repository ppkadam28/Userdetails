package com.simplyfleet.assignment.network

class ApiHelper(private val apiHelper: ApiService){

    suspend fun getList() = apiHelper.getListDetails()

}