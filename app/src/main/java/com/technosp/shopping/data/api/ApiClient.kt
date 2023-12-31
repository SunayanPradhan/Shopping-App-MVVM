package com.technosp.shopping.data.api

import com.technosp.shopping.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        private lateinit var apiService: ApiService

        fun getApiService(): ApiService {

            if (!Companion::apiService.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }

            return apiService

        }
    }

}