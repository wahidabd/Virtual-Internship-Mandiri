package com.wahidabd.mandirinews.di

import com.wahidabd.mandirinews.data.ApiService
import com.wahidabd.mandirinews.data.source.NewsDataSource
import com.wahidabd.mandirinews.data.source.NewsRepository
import com.wahidabd.mandirinews.domain.NewsInteractor
import com.wahidabd.mandirinews.domain.NewsUseCase
import com.wahidabd.mandirinews.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


val newsModule = module {
    single {
        com.wahidabd.library.data.libs.ApiService.createService(
            get(), get(named(BASE_URL))
        )
    }

    single { get<Retrofit>().create(ApiService::class.java) }
    single<NewsRepository> { NewsDataSource(get()) }
    single<NewsUseCase> { NewsInteractor(get()) }
    viewModel { HomeViewModel(get()) }
}