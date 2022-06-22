package com.example.ecommerceapp.data.remote.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    @SerializedName("currentStore")
    val currentStore: Store?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("newPrice")
    val newPrice: Double?,
    @SerializedName("oldPrice")
    val oldPrice: Double?,
    @SerializedName("productImage")
    val productImage: String?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("type")
    val type: String?
) : Parcelable

@Parcelize
data class Store(
    val storeName: String? = null,
    val lat: String? = null,
    val longitude: String? = null
) : Parcelable