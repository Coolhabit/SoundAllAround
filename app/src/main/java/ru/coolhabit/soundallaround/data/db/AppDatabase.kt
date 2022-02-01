package ru.coolhabit.soundallaround.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.coolhabit.soundallaround.data.dao.AlbumsDAO
import ru.coolhabit.soundallaround.data.entity.ResultAlbums

@Database(entities = [ResultAlbums::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumsDao(): AlbumsDAO
}