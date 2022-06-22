package com.example.ecommerceapp.ui.basket

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.data.local.ClientPreferences
import com.example.ecommerceapp.data.remote.response.ProductItem
import com.example.ecommerceapp.databinding.FragmentBasketBinding
import com.example.ecommerceapp.ui.basket.adapter.BasketListAdapter
import com.example.ecommerceapp.util.base.BaseFragment
import com.example.ecommerceapp.util.const.Constant.BASKET_ID
import com.example.ecommerceapp.util.extension.OnItemClickListener
import com.example.ecommerceapp.util.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding,BasketViewModel>(
    FragmentBasketBinding::inflate
) {
    override val viewModel by viewModels<BasketViewModel>()
    private var basketList = mutableListOf<ProductItem>()
    private var temporaryList = mutableListOf<ProductItem>()

    override fun onCreateFinished() {
        getBasket()
        setRecyclerViewAdapter()
    }

    override fun initListeners() {
        binding.btnBuy.setOnClickListener {
            clearBasket()
        }
    }

    override fun observeEvents() {
        with(viewModel){
            getBasketResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    basketList.clear()
                    it.result.products?.forEach { basketProduct ->
                        basketList.add(basketProduct)
                    }
                    temporaryList.clear()
                    temporaryList.addAll(basketList)
                    (binding.rvBasketProducts.adapter as BasketListAdapter).updateList(temporaryList)
                }
            })
            clearBasketResponse.observe(viewLifecycleOwner, Observer {
                if(it.statusCode == 200){
                    ClientPreferences(requireContext()).clearCard()
                    snack(requireView(),"Satın alma başarılı.")
                    getBasket()
                }
            })

            removeProductResponse.observe(viewLifecycleOwner, Observer {

            })
        }
    }

    private fun setRecyclerViewAdapter(){
        val mLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvBasketProducts.layoutManager = mLayoutManager

        val mAdapter = BasketListAdapter(object: OnItemClickListener{
            override fun onClick(product: ProductItem) {
                removeProductFromBasket(product.id.toString())
            }
        })
        binding.rvBasketProducts.adapter = mAdapter
    }

    private fun getBasket(){
        viewModel.getBasket(BASKET_ID)
    }

    private fun removeProductFromBasket(productId : String){
        viewModel.removeProductFromBasket(BASKET_ID,productId)
    }

    private fun clearBasket(){
        viewModel.clearBasket(BASKET_ID)
    }
}