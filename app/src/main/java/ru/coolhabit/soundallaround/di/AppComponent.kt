package ru.coolhabit.soundallaround.di

import ru.coolhabit.soundallaround.di.modules.DatabaseModule
import ru.coolhabit.soundallaround.di.modules.DomainModule
import ru.coolhabit.soundallaround.di.modules.RemoteModule
import ru.coolhabit.soundallaround.viewmodel.DetailsFragmentViewModel
import ru.coolhabit.soundallaround.viewmodel.HomeFragmentViewModel

import dagger.Component
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