package com.example.ecommerceapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.data.remote.response.ProductItem
import com.example.ecommerceapp.databinding.ItemProductBinding
import com.example.ecommerceapp.util.extension.OnItemClickListener

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
class ProductListAdapter(
    private val onItemClickListener : OnItemClickListener
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var productList = mutableListOf<ProductItem>()

    class ViewHolder(private val itemBinding : ItemProductBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(listener : OnItemClickListener, productItem: ProductItem){
            itemBinding.onItemClickListener = listener
            itemBinding.productItem = productItem
            itemBinding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater,parent,false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onItemClickListener, productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun updateList(_productList : MutableList<ProductItem>){
        productList = _productList
        notifyDataSetChanged()
    }
}