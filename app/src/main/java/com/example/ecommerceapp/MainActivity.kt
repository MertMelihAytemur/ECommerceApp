package com.example.ecommerceapp

import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.ecommerceapp.data.local.ClientPreferences
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.util.extension.snack
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        checkBasketEmpty()
    }

    private fun initListener() {
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.toolbar.ivBasket.setOnClickListener {

        }
    }

    fun checkBasketEmpty() {
        val basketCount = ClientPreferences(this).getBasketCount()
        snack(binding.root, basketCount.toString())
        if (basketCount > 0) {
            binding.toolbar.ivBasket.background = getDrawable(R.drawable.ic_full_basket)
        } else {
            binding.toolbar.ivBasket.background = getDrawable(R.drawable.ic_basket)
        }
    }

    fun isEnabledBackButton(isEnabled: Boolean) {
        if (isEnabled) {
            binding.toolbar.ivBack.visibility = View.VISIBLE
        } else {
            binding.toolbar.ivBack.visibility = View.GONE
        }
    }
}