package ru.coolhabit.soundallaround.data

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.coolhabit.soundallaround.data.entity.SearchAlbums
import ru.coolhabit.soundallaround.data.entity.SearchTracks

private const val SEARCH = "search"
private const val LOOKUP = "lookup"
private const val TERM = "term"
private const val ENTITY = "entity"
private const val ID = "id"

interface AlbumsApi {
    @GET(SEARCH)
    fun getAlbums(
        @Query(TERM) searchResult: String,
        @Query(ENTITY) album: String
    ): Observable<SearchAlbums>

    @GET(LOOKUP)
    fun getTracks(
        @Query(ID) album: String,
        @Query(ENTITY) track: String
    ): Observable<SearchTracks>
}