package com.example.miky.dagger.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Single
import com.example.miky.dagger.data.GetListResult
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

@Module
class NetworkModule {

    @Provides
    fun provideResrofitService(): JokeRetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://api.icndb.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeRetrofitService::class.java)
    }
}

interface JokeRetrofitService {
    @GET("jokes/random/20")
    fun getList(): Single<GetListResult>
}
