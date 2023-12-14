package com.niveditha.tictactoe.runtime

import android.app.Application
import android.content.Context
import com.niveditha.tictactoe.BuildConfig
import com.niveditha.tictactoe.extension.app
import com.niveditha.tictactoe.extension.appContext
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TicTacToeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        app = this
        appContext = base
    }
}