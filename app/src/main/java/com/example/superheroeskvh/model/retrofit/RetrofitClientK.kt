package com.example.superheroeskvh.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientK {

    companion object {
        private const val BASE_URL = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/"

        fun retrofitInstance(): ApiAkabab {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiAkabab::class.java)

        }
    }
}