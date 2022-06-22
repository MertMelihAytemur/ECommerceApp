package com.example.ecommerceapp.util.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.ecommerceapp.util.extension.loadImageView

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
class ImageViewBindingAdapter {
    companion object{
        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView : ImageView, imageUrl : String){
            imageView.loadImageView(imageUrl)
        }
    }
}