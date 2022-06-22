package com.example.ecommerceapp.util.extension

import com.example.ecommerceapp.data.remote.response.ProductItem

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
interface OnItemClickListener {
    fun onClick(product : ProductItem)
}