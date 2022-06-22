package com.example.ecommerceapp.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.remote.response.BaseResponse
import com.example.ecommerceapp.data.remote.response.BasketResponse
import com.example.ecommerceapp.repository.BasketRepository
import com.example.ecommerceapp.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
@HiltViewModel
class BasketViewModel @Inject constructor(private val basketRepository: BasketRepository) : ViewModel() {
    var clearBasketResponse : MutableLiveData<BaseResponse<BasketResponse>> = MutableLiveData()
    var removeProductResponse : MutableLiveData<BaseResponse<BasketResponse>> = MutableLiveData()
    var getBasketResponse : MutableLiveData<BaseResponse<BasketResponse>> = MutableLiveData()
    var onLoading : MutableLiveData<Boolean> = MutableLiveData()
    val onError : MutableLiveData<String?> = MutableLiveData()

    fun clearBasket(
        basketId : String
    ) = viewModelScope.launch {

    }

    fun removeProductFromBasket(
        basketId: String,
        productId: String
    ) = viewModelScope.launch {

    }

    fun getBasket(
        basketId: String
    ) = viewModelScope.launch {
        onLoading.value = true
        val request = basketRepository.getBasket(basketId)

        when(request){
            is NetworkResult.Success ->{
                onLoading.value = false
                getBasketResponse.value = request.data!!
            }
            is NetworkResult.Error ->{
                onLoading.value = false
                onError.value = request.message
            }
        }
    }
}