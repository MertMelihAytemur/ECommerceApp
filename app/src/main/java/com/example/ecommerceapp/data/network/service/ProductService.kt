package com.example.ecommerceapp.data.network.service

import com.example.ecommerceapp.data.remote.response.BaseResponse
import com.example.ecommerceapp.data.remote.response.BasketResponse
import com.example.ecommerceapp.data.remote.response.ProductItem
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *Created by Mert Melih Aytemur on 21.06.2022.
 */
interface ProductService {

    @GET("/products")
    suspend fun getAllProducts(): BaseResponse<MutableList<ProductItem>>

    @GET("/products/{id}")
    suspend fun getProductsDetail(
        @Path("id") productId: String
    ): BaseResponse<ProductItem>

    @POST("/addtocart/{id}/{prodID}")
    suspend fun addToBasket(
        @Path("id") basketId :String,
        @Path("prodID") productId: String
    ) : BaseResponse<BasketResponse>

    @GET("/clearcart/{id}")
    suspend fun clearBasket(
        @Path("id") basketId : String
    ) : BaseResponse<BasketResponse>

    @GET("/removeproduct/{id}/{productID}")
    suspend fun removeProductFromBasket(
        @Path("id") basketId: String,
        @Path("prodID") productId: String
    ) : BaseResponse<BasketResponse>

    @GET("/cart/{id}")
    suspend fun getBasket(
        @Path("id") basketId: String
    ) : BaseResponse<BasketResponse>
}