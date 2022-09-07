package com.example.unittest

import android.app.Application
import com.example.unittest.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)
            // use modules
            modules(AppModule().getAppModule())
            modules(AppModule().getNetworkModule(BuildConfig.BASE_URL))
        }
    }
}