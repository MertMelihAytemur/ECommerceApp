package com.example.ecommerceapp.ui.basket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.data.remote.response.ProductItem
import com.example.ecommerceapp.databinding.ItemBasketBinding
import com.example.ecommerceapp.util.extension.OnItemClickListener

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
class BasketListAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<BasketListAdapter.ViewHolder>() {
    private var basketList = mutableListOf<ProductItem>()

    class ViewHolder(private val itemBinding : ItemBasketBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(listener : OnItemClickListener,product: ProductItem){
            itemBinding.onItemClickListener = listener
            itemBinding.basketItem = product
            itemBinding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBasketBinding.inflate(layoutInflater,parent,false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onItemClickListener,basketList[position])
    }

    override fun getItemCount(): Int = basketList.size

    fun updateList(_basketList : MutableList<ProductItem>){
        basketList = _basketList
        notifyDataSetChanged()
    }
}