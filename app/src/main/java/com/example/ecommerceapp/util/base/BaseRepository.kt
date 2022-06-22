package com.example.ecommerceapp.util.base

import com.example.ecommerceapp.util.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
*Created by Mert Melih Aytemur on 22.06.2022.
*/
abstract class BaseRepository {
    suspend fun <T> safeApiRequest(
        apiRequest: suspend () -> T
    ) : NetworkResult<T>{
        return withContext(Dispatchers.IO){
            try {
                NetworkResult.Success(apiRequest.invoke())
            }catch (throwable : Throwable){
                when(throwable){
                    is HttpException ->{
                        NetworkResult.Error(false,throwable.response()?.errorBody()?.toString())
                    }
                    else -> NetworkResult.Error(true,throwable.localizedMessage)
                }
            }
        }
    }
}