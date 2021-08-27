package com.dicoding.foodsapp

import android.app.Application
import com.dicoding.foodsapp.core.BuildConfig
import com.dicoding.foodsapp.di.useCaseModule
import com.dicoding.foodsapp.di.viewModelModule
import com.dicoding.foodsmapp.core.di.databaseModule
import com.dicoding.foodsmapp.core.di.networkModule
import com.dicoding.foodsmapp.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}