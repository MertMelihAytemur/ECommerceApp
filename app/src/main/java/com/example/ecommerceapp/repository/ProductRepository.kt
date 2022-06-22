package com.example.ecommerceapp.repository

import com.example.ecommerceapp.data.network.service.ProductService
import com.example.ecommerceapp.util.base.BaseRepository
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
class ProductRepository @Inject constructor(private val productService: ProductService) : BaseRepository() {

    suspend fun getAllProducts() = safeApiRequest {
        productService.getAllProducts()
    }

    suspend fun getProductDetail(
        productId : String
    ) = safeApiRequest {
        productService.getProductsDetail(productId)
    }

    suspend fun addToBasket(
        basketId : String,
        productId: String
    ) = safeApiRequest {
        productService.addToBasket(basketId,productId)
    }
}