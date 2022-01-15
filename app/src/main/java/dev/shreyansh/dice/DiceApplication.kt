@file:Suppress("unused")

package dev.shreyansh.dice

import android.app.Application
import timber.log.Timber

class DiceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}