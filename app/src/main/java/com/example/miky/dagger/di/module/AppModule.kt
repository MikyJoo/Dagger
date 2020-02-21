package com.example.miky.dagger.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import com.example.miky.dagger.Application
import com.example.miky.dagger.data.JokeRepository
import com.example.miky.dagger.main.MainViewModel
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }
}