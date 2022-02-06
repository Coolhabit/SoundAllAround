package ru.coolhabit.soundallaround

import android.app.Application
import ru.coolhabit.soundallaround.di.AppComponent
import ru.coolhabit.soundallaround.di.DaggerAppComponent
import ru.coolhabit.soundallaround.di.modules.DatabaseModule
import ru.coolhabit.soundallaround.di.modules.DomainModule
import ru.coolhabit.soundallaround.di.modules.RemoteModule

class App : Application() {

    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule(this))
            .domainModule(DomainModule())
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}