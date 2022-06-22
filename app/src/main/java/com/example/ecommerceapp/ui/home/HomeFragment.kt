package com.example.ecommerceapp.ui.home


import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.MainActivity
import com.example.ecommerceapp.data.remote.response.ProductItem
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.ui.home.adapter.ProductListAdapter
import com.example.ecommerceapp.util.base.BaseFragment
import com.example.ecommerceapp.util.extension.OnItemClickListener
import com.example.ecommerceapp.util.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()
    private val productList: MutableList<ProductItem> = mutableListOf()
    private val temporaryList: MutableList<ProductItem> = mutableListOf()


    override fun onCreateFinished() {
        viewModel.getAllProducts()
        setRecyclerViewAdapter()
    }

    override fun initListeners() {
        (activity as MainActivity).isEnabledBackButton(false)
        (activity as MainActivity).checkBasketEmpty()
    }

    override fun observeEvents() {
        with(viewModel){
            productListResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    productList.clear()
                    it.result.forEach { product ->
                        productList.add(product)
                    }
                }
                temporaryList.clear()
                temporaryList.addAll(productList)
                (binding.rvProducts.adapter as ProductListAdapter).updateList(temporaryList)
            })

            onLoading.observe(viewLifecycleOwner, Observer {
                handleViewActions(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })
        }
    }

    private fun setRecyclerViewAdapter() {
        val mLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvProducts.layoutManager = mLayoutManager
        val mAdapter = ProductListAdapter(object : OnItemClickListener{
            override fun onClick(product: ProductItem) {
                val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(product)
                findNavController().navigate(action)
            }
        })
        binding.rvProducts.adapter = mAdapter
    }

    private fun handleViewActions(isLoading: Boolean = false) {
        binding.rvProducts.isVisible = !isLoading
        binding.progressBar.isVisible = isLoading
    }

}