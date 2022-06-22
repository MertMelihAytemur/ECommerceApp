package com.example.ecommerceapp.util.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.ecommerceapp.R
import java.lang.IllegalArgumentException

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
abstract class BaseFragment<VB : ViewBinding,VM : ViewModel>(
    private val bindingInflater : (inflater : LayoutInflater) -> VB
) : Fragment() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB

    protected abstract val viewModel: VM
    protected abstract fun onCreateFinished()
    protected abstract fun initListeners()
    protected abstract fun observeEvents()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null) {
            throw IllegalArgumentException("Binding is null.")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateFinished()
        initListeners()
        observeEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


