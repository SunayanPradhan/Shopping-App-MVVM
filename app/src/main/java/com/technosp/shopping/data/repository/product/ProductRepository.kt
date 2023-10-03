package com.technosp.shopping.data.repository.product

import com.technosp.shopping.data.model.Product
import retrofit2.Call

interface ProductRepository {

    fun getProducts(): Call<List<Product>>

}