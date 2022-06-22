package com.example.ecommerceapp.data.local

import android.content.Context

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
class ClientPreferences(context: Context) {
    companion object{
        private const val PREFERENCES_NAME = "Akademi"
        private const val BASKET_COUNT = "basketCount"
        private const val BASKET_TOKEN = "basketToken"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE)
    }

    fun getBasketCount(): Int = sharedPreferences.getInt(BASKET_COUNT, 0)

    fun setBasketCount(basketCount: Int) {
        with(sharedPreferences.edit()) {
            basketCount.let {
                putInt(BASKET_COUNT, it)
                apply()
            }
        }
    }

    fun getBasketToken(): Int = sharedPreferences.getInt(BASKET_TOKEN, 0)

    fun setBasketToken(basketToken: Int) {
        with(sharedPreferences.edit()) {
            basketToken.let {
                putInt(BASKET_TOKEN, it)
                apply()
            }
        }
    }

    fun clearCard() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}