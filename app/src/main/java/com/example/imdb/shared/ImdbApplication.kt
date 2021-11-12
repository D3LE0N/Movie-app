package com.example.imdb.shared

import android.app.Application
import com.example.imdb.dagger.DaggerIApplicationComponent
import com.example.imdb.dagger.IApplicationComponent


class ImdbApplication : Application() {

    val component: IApplicationComponent by lazy {
        DaggerIApplicationComponent
            .factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}