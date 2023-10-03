package com.technosp.shopping.data.repository.product

import com.technosp.shopping.data.api.ApiService
import com.technosp.shopping.data.model.Product
import com.technosp.shopping.data.repository.product.ProductRepository
import retrofit2.Call

class ProductRepositoryImpl constructor(private val apiService: ApiService) : ProductRepository {

    override fun getProducts(): Call<List<Product>> {
        return apiService.getProducts()
    }

}