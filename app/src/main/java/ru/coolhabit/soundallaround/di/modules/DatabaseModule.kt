package ru.coolhabit.soundallaround.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.coolhabit.soundallaround.data.MainRepository
import ru.coolhabit.soundallaround.data.dao.AlbumsDAO
import ru.coolhabit.soundallaround.data.db.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideNewsDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "albums_db"
        ).build().albumsDao()

    @Provides
    @Singleton
    fun provideRepository(albumsDAO: AlbumsDAO) = MainRepository(albumsDAO)
}