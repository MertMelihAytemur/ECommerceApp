package com.example.ecommerceapp.util.bindingadapter

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */

@BindingAdapter("setPrice")
fun setPrice(textView: TextView, price: String?) {
    price?.let {
        textView.text = "₺$price"
    }
}

@BindingAdapter("strikeThrough")
fun strikeThrough(textView: TextView,strikeTrough : Boolean){
    if(strikeTrough){
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }else{
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
