package ru.coolhabit.soundallaround.data.entity

data class SearchTracks(
    val resultCount: Int,
    val results: List<ResultTracks>
)