package com.example.ecommerceapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.remote.response.BaseResponse
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
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {
    val productListResponse : MutableLiveData<BaseResponse<MutableList<ProductItem>>> = MutableLiveData()
    val onLoading : MutableLiveData<Boolean> = MutableLiveData()
    val onError : MutableLiveData<String?> = MutableLiveData()

    fun getAllProducts() = viewModelScope.launch {
        onLoading.value = true
        val request = productRepository.getAllProducts()

        when(request){
            is NetworkResult.Success ->{
                onLoading.value = false
                productListResponse.value = request.data!!
            }
            is NetworkResult.Error ->{
                onLoading.value = false
                onError.value = request.message
            }
        }
    }
}