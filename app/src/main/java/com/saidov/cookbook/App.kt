package com.saidov.cookbook

import android.app.Application
import com.saidov.cookbook.core.di.repositoryModule
import com.saidov.cookbook.core.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * Created by MUHAMMADJON SAIDOV on 02,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, vmModule))
        }
    }
}