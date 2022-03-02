package com.saidov.cookbook.repository.networkrepository.repo

import com.saidov.cookbook.other.Constants.Companion.BASE_URL
import com.saidov.cookbook.repository.networkrepository.api.CookService
import com.saidov.cookbook.repository.networkrepository.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by MUHAMMADJON SAIDOV on 31,январь,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */

class NetworkRepositoryImpl() : INetworkRepository {

    private fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient
            .Builder()
            .addInterceptor(logging)
            .addNetworkInterceptor(ApiKeyInterceptor())
            .build()
        return client
    }

    override fun getApi(): CookService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CookService::class.java)
        return retrofit
    }
}