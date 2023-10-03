package com.technosp.shopping.ui.main.search.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.technosp.shopping.data.model.Product
import com.technosp.shopping.utils.Constants
import com.technosp.shopping.R
import com.technosp.shopping.databinding.ItemProductSearchBinding

class SearchAdapter(
      private val navController: NavController,
      private val productList: List<Product>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private lateinit var binding: ItemProductSearchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = ItemProductSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    fun goProductDetails(product: Product?){

        navController.navigate(
            R.id.action_searchFragment_to_productDetailsFragment,
            Bundle().apply {
                putString(Constants.PRODUCT_MODEL_NAME, product?.toJson())
            })

    }

    inner class SearchViewHolder(private val binding: ItemProductSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.dataHolder = product
            binding.searchAdapter = this@SearchAdapter
            binding.executePendingBindings()
        }

    }

    override fun getItemCount(): Int = productList.size

}