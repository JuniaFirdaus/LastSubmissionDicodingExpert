package com.dicoding.foodsmapp.core.di

import androidx.room.Room
import com.dicoding.foodsmapp.core.data.FoodRepository
import com.dicoding.foodsmapp.core.data.source.local.LocalDataSource
import com.dicoding.foodsmapp.core.data.source.local.room.FoodDatabase
import com.dicoding.foodsmapp.core.data.source.remote.RemoteDataSource
import com.dicoding.foodsmapp.core.data.source.remote.network.ApiService
import com.dicoding.foodsmapp.core.domain.repository.IFoodRepository
import com.dicoding.foodsmapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FoodDatabase>().foodDao() }

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FoodDatabase::class.java, "Foods.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "themealdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/tbb5eUN4H4PLVKGFjsN8Jp2CsBwwoC6GzxIbpn0HNM=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IFoodRepository> {
        FoodRepository(
            get(),
            get(),
            get()
        )
    }
}