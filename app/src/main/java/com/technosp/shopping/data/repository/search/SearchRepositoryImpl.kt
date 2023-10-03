package com.technosp.shopping.data.repository.search

import com.technosp.shopping.data.api.ApiService
import com.technosp.shopping.data.model.Product
import com.technosp.shopping.data.repository.search.SearchRepository
import retrofit2.Call

class SearchRepositoryImpl constructor(private val apiService: ApiService) : SearchRepository {

    override fun getProducts(): Call<List<Product>> {
        return apiService.getProducts()
    }

    override fun getCategories(): Call<List<String>> {
        return apiService.getCategories()
    }

    override fun getProductsByCategory(category: String): Call<List<Product>> {
        return apiService.getProductsByCategory(category)
    }

}