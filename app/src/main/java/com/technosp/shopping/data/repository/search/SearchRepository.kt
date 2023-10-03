package com.technosp.shopping.data.repository.search

import com.technosp.shopping.data.model.Product
import retrofit2.Call

interface SearchRepository {

    fun getProducts(): Call<List<Product>>

    fun getProductsByCategory(category: String): Call<List<Product>>

    fun getCategories(): Call<List<String>>

}