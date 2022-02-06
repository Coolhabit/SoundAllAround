package ru.coolhabit.soundallaround.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import ru.coolhabit.soundallaround.App
import ru.coolhabit.soundallaround.domain.Interactor
import javax.inject.Inject

class DetailsFragmentViewModel : ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    fun getTracks(albumId: String): Observable<List<String>> = interactor.getTracksFromApi(albumId)
}