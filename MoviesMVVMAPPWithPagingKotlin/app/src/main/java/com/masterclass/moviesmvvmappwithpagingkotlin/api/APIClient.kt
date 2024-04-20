package com.masterclass.moviesmvvmappwithpagingkotlin.api

import com.masterclass.moviesmvvmappwithpagingkotlin.utils.Utils.API_KEY
import com.masterclass.moviesmvvmappwithpagingkotlin.utils.Utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private var apiInterface: APIInterface? = null
    fun getApiInterface(): APIInterface? {
        if (apiInterface == null) {
            val client = OkHttpClient.Builder()
            client.addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
            apiInterface = retrofit.create(APIInterface::class.java)
        }
        return apiInterface
    }
}
