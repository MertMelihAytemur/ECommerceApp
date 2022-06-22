package com.example.ecommerceapp.util.extension

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */

fun Activity.toast(message: String?, duration: Int = Toast.LENGTH_LONG) {
    message?.let {
        Toast.makeText(this, it, duration).show()
    }
}
fun Activity.snack(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(view,message,duration).show()
}