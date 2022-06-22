package com.example.ecommerceapp.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
data class BaseResponse<T>(
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: T,
    @SerializedName("status")
    val status: String?,
    @SerializedName("statusCode")
    val statusCode: Int?
)
