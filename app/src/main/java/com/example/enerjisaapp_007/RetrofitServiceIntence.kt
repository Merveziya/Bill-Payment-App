package com.example.enerjisaapp_007

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitServiceInstance {
    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"

        private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}