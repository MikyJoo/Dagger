package com.example.miky.dagger.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import com.example.miky.dagger.di.module.AppModule
import com.example.miky.dagger.di.module.NetworkModule
import com.example.miky.dagger.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}