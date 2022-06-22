package com.example.ecommerceapp.data.remote.response

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
data class BasketResponse(
    val id: Int? = null,
    val token: String? = null,
    val products: MutableList<ProductItem>? = mutableListOf()
)
