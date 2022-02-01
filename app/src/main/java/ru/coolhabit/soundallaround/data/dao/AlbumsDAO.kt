package ru.coolhabit.soundallaround.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumsDAO {
    @Query("SELECT * FROM search_albums")
    fun getCachedNews(): LiveData<List<ResultAlbums>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ResultAlbums>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(resultAlbums: ResultAlbums)

    @Query("DELETE FROM search_albums")
    fun deleteAll()
}