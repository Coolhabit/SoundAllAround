package ru.coolhabit.soundallaround.di

import dagger.Component
import ru.coolhabit.soundallaround.di.modules.DatabaseModule
import ru.coolhabit.soundallaround.di.modules.DomainModule
import ru.coolhabit.soundallaround.di.modules.RemoteModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)

interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(detailsFragmentViewModel: DetailsFragmentViewModel)
}