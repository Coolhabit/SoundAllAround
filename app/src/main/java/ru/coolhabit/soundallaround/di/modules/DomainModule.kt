package ru.coolhabit.soundallaround.di.modules

import dagger.Module
import dagger.Provides
import ru.coolhabit.soundallaround.data.AlbumsApi
import ru.coolhabit.soundallaround.data.MainRepository
import ru.coolhabit.soundallaround.domain.Interactor
import javax.inject.Singleton

@Module
class DomainModule() {

    @Provides
    @Singleton
    fun provideInteractor(repository: MainRepository, albumsApi: AlbumsApi, )
    = Interactor(repo = repository, retrofitService = albumsApi)
}