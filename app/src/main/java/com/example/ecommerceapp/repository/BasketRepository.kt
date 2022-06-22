package com.example.ecommerceapp.repository

import com.example.ecommerceapp.data.network.service.ProductService
import com.example.ecommerceapp.util.base.BaseRepository
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
class BasketRepository @Inject constructor(private val productService: ProductService): BaseRepository() {

    suspend fun clearBasket(
        basketId: String
    ) = safeApiRequest {
        productService.clearBasket(basketId)
    }

    suspend fun removeProductFromBasket(
        basketId: String,
        productId: String
    ) = safeApiRequest {
        productService.removeProductFromBasket(basketId,productId)
    }

    suspend fun getBasket(
        basketId: String
    ) = safeApiRequest {
        productService.getBasket(basketId)
    }
}