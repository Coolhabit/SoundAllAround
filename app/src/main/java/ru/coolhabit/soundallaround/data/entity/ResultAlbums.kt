package ru.coolhabit.soundallaround.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "search_albums", indices = [Index(value = ["collectionName"], unique = true)])
data class ResultAlbums(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "artistName", defaultValue = "none") var artistName: String,
    @ColumnInfo(name = "artworkUrl100", defaultValue = "none") var artworkUrl100: String,
    @ColumnInfo(name = "collectionId", defaultValue = "none") var collectionId: Int,
    @ColumnInfo(name = "collectionName", defaultValue = "none") var collectionName: String,
    @ColumnInfo(name = "collectionType", defaultValue = "none") var releaseDate: String,
    @ColumnInfo(name = "primaryGenreName", defaultValue = "none") var primaryGenreName: String,
    @ColumnInfo(name = "trackCount", defaultValue = "none") var trackCount: Int
) : Parcelable
