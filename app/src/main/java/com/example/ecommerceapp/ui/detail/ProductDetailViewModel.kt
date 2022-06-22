package com.example.ecommerceapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.remote.response.BaseResponse
import com.example.ecommerceapp.data.remote.response.BasketResponse
import com.example.ecommerceapp.data.remote.response.ProductItem
import com.example.ecommerceapp.repository.ProductRepository
import com.example.ecommerceapp.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {
    var productDetailResponse : MutableLiveData<BaseResponse<ProductItem>> = MutableLiveData()
    var addToBasketResponse : MutableLiveData<BaseResponse<BasketResponse>> = MutableLiveData()
    var onLoading : MutableLiveData<Boolean> = MutableLiveData()
    var onError : MutableLiveData<String?> = MutableLiveData()

    fun getProductDetail(
        productId : String
    ) = viewModelScope.launch {
        onLoading.value = true

        val request = productRepository.getProductDetail(productId)

        when(request){
            is NetworkResult.Success ->{
                onLoading.value = false
                productDetailResponse.value = request.data!!
            }
            is NetworkResult.Error ->{
                onLoading.value = false
                onError.value = request.message
            }
        }
    }

    fun addToBasket(
        basketId : String,
        productId: String
    ) = viewModelScope.launch {
        val request = productRepository.addToBasket(basketId,productId)

        when(request){
            is NetworkResult.Success ->{
                addToBasketResponse.value = request.data!!
            }
        }
    }
}