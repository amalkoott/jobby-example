package ru.amalkoott.findjob

import android.app.Application
import dagger.Component
import dagger.hilt.android.HiltAndroidApp
import ru.amalkoott.core.di.AppModule
import javax.inject.Singleton

@HiltAndroidApp
class Application : Application() {
}