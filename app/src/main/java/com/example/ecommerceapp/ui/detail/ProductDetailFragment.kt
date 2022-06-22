package com.example.ecommerceapp.ui.detail


import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ProcessLifecycleInitializer
import androidx.navigation.fragment.navArgs
import com.example.ecommerceapp.MainActivity
import com.example.ecommerceapp.data.local.ClientPreferences
import com.example.ecommerceapp.databinding.FragmentProductDetailBinding
import com.example.ecommerceapp.util.base.BaseFragment
import com.example.ecommerceapp.util.const.Constant.BASKET_ID
import com.example.ecommerceapp.util.extension.loadImageView
import com.example.ecommerceapp.util.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>(
    FragmentProductDetailBinding::inflate
) {
    override val viewModel by viewModels<ProductDetailViewModel>()
    private val args by navArgs<ProductDetailFragmentArgs>()


    override fun onCreateFinished() {
        viewModel.getProductDetail(args.productDetail.id.toString())
    }

    override fun initListeners() {
        binding.btnSeeStore.setOnClickListener {

        }

        binding.btnAddToCart.setOnClickListener {
            viewModel.addToBasket(BASKET_ID, args.productDetail.id.toString())
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            productDetailResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    binding.ivProductPhoto.loadImageView(it.result.productImage.toString())
                    binding.tvProductName.text = it.result.productName.toString()
                    binding.tvProductOldPrice.text = it.result.oldPrice.toString()
                    binding.tvProductNewPrice.text = it.result.newPrice.toString()
                }
            })

            onLoading.observe(viewLifecycleOwner, Observer {
                handleViewActions(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(), it.toString())
            })

            addToBasketResponse.observe(viewLifecycleOwner, Observer {
                var basketCount = ClientPreferences(requireContext()).getBasketCount()
                ClientPreferences(requireContext()).setBasketCount(++basketCount)
                snack(requireView(), it.status.toString())
            })

        }
    }

    private fun handleViewActions(isLoading: Boolean = false) {
        binding.btnAddToCart.isVisible = !isLoading
        binding.btnSeeStore.isVisible = !isLoading
        binding.progressBar.isVisible = isLoading
    }

}