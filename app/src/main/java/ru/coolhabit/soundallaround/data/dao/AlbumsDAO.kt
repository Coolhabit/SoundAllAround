package ru.coolhabit.soundallaround.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import ru.coolhabit.soundallaround.data.entity.ResultAlbums

@Dao
interface AlbumsDAO {
    @Query("SELECT * FROM search_albums")
    fun getCachedNews(): Observable<List<ResultAlbums>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ResultAlbums>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(resultAlbums: ResultAlbums)

    @Query("DELETE FROM search_albums")
    fun deleteAll()
}