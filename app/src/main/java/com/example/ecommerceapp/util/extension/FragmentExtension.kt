package com.example.ecommerceapp.util.extension

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */

fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_LONG) {
    message?.let {
        Toast.makeText(requireContext(), it, duration).show()
    }
}
fun Fragment.snack(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(view,message,duration).show()
}

val FragmentManager.currentNavigationFragment: Fragment?
    get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()