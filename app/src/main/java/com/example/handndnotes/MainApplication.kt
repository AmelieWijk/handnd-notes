package com.example.handndnotes

import android.app.Application
import com.example.handndnotes.customize.CustomizeModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(models)
        }
    }
}

private val models= module {
    singleOf(::PageHandler)
    singleOf(::CustomizeModel)
}

