package com.wahidabd.mandirinews

import com.wahidabd.library.presentation.BaseApplication
import com.wahidabd.mandirinews.di.apiModule
import com.wahidabd.mandirinews.di.newsModule
import org.koin.core.module.Module
import timber.log.Timber


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


class App : BaseApplication() {

    override fun getDefineModule(): List<Module> =
        listOf(
            apiModule,
            newsModule
        )

    override fun initApp() {
        Timber.plant(Timber.DebugTree())
    }
}