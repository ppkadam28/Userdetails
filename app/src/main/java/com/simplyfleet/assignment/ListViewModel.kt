package com.simplyfleet.assignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.simplyfleet.assignment.network.ApiHelper
import com.simplyfleet.assignment.network.Resource

import kotlinx.coroutines.Dispatchers

class ListViewModel(private val mainRepository: ApiHelper) : ViewModel() {

    fun getListDetails() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }



//    fun uploadBackup(token: String, appUserId: Int?, UserPhoto: Uri?) =
//        liveData(Dispatchers.IO) {
//            emit(Resource.loading(data = null))
//            try {
//                emit(
//                    Resource.success(
//                        data = mainRepository.addUserImage(
//                            token,
//                            appUserId,
//                            UserPhoto
//                        )
//                    )
//                )
//            } catch (exception: Exception) {
//                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//            }
//        }


}