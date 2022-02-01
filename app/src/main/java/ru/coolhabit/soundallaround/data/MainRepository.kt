package ru.coolhabit.soundallaround.data

import io.reactivex.rxjava3.core.Observable
import ru.coolhabit.soundallaround.data.dao.AlbumsDAO
import ru.coolhabit.soundallaround.data.entity.ResultAlbums

class MainRepository(private val albumsDAO: AlbumsDAO) {

    fun putToDb(resultAlbums: List<ResultAlbums>) {
        albumsDAO.insertAll(resultAlbums)
    }

    fun getAllFromDB(): Observable<List<ResultAlbums>> = albumsDAO.getCachedNews()

    fun deleteAll() = albumsDAO.deleteAll()
}