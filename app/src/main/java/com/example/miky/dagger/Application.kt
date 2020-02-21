package com.example.miky.dagger

import android.app.Application
import com.example.miky.dagger.di.component.AppComponent
import com.example.miky.dagger.di.component.DaggerAppComponent
import com.example.miky.dagger.di.module.AppModule

class Application: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}