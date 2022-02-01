package ru.coolhabit.soundallaround.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.coolhabit.soundallaround.data.AlbumsApi
import ru.coolhabit.soundallaround.data.MainRepository
import ru.coolhabit.soundallaround.data.entity.ResultAlbums

private const val ALBUM_ENTITY = "album"
private const val TRACK_ENTITY = "song"

class Interactor(
    private val repo: MainRepository,
    private val retrofitService: AlbumsApi,
) {

    fun getAlbumsFromApi(search: String): Observable<List<ResultAlbums>> {

        val result = retrofitService.getAlbums(search, ALBUM_ENTITY)
            .subscribeOn(Schedulers.io())
            .map { searchAlbums ->
                val sortedAlbums = searchAlbums.results
                sortedAlbums.sortedBy { it.collectionName }
            }.doOnError {
                println("Ошибка интернета или сервера")
            }
            .doOnNext {
                repo.deleteAll()
                repo.putToDb(it)
            }
        return result
    }

    fun getTracksFromApi(albumId: String): Observable<List<String>> = retrofitService
        .getTracks(albumId, TRACK_ENTITY)
        .subscribeOn(Schedulers.io())
        .map {
            it.results
        }
        .map { list ->
            val tracksNameList = mutableListOf<String>()
            list.forEach { tracksNameList.add(it.trackName) }
            tracksNameList
        }

    fun getAlbumsFromDB(): Observable<List<ResultAlbums>> = repo.getAllFromDB()
}