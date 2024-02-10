package com.simplyfleet.assignment.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.simplyfleet.assignment.ListViewModel


class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(ApiHelper(apiService)) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}