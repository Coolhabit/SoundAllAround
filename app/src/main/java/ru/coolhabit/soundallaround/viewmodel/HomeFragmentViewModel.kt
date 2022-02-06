package ru.coolhabit.soundallaround.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import ru.coolhabit.soundallaround.App
import ru.coolhabit.soundallaround.data.entity.ResultAlbums
import ru.coolhabit.soundallaround.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val albumsListData: Observable<List<ResultAlbums>>

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        albumsListData = interactor.getAlbumsFromDB()
    }

    fun getAlbums(search: String) = interactor.getAlbumsFromApi(search)
}
