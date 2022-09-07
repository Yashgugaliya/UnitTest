package com.example.unittest.di

import com.example.unittest.network.ApiInterface
import com.example.unittest.repository.MainRepository
import com.example.unittest.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppModule {
    fun getAppModule():Module{
        return module {
            single { MainRepository(get()) }
            viewModel{ MainViewModel(get()) }
        }
    }

    fun getNetworkModule(baseUrl:String) = module{
        single {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
        }
        factory {
            Retrofit.Builder()
                .client(get())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        single { get<Retrofit>().create(ApiInterface::class.java) }
    }
}