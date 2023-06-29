package com.wahidabd.mandirinews.di

import com.wahidabd.library.data.libs.OkHttpClientFactory
import com.wahidabd.library.data.libs.interceptor.ParameterInterceptor
import com.wahidabd.mandirinews.BuildConfig
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


const val BASE_URL = ""

val apiModule = module {

    single {
        return@single OkHttpClientFactory.create(
            interceptors = listOf(getParameterInterceptor()),
            showDebugLog = BuildConfig.DEBUG,
            authenticator = null,
            certificatePinner = null
        )
    }

    single(named(BASE_URL)) {
        BuildConfig.BASE_URL
    }

}

private fun getParameterInterceptor(): Interceptor {
    val params = HashMap<String, String>()
    params["apiKey"] = BuildConfig.API_KEY
    return ParameterInterceptor(params)
}