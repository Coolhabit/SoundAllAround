package ru.coolhabit.soundallaround.view.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.coolhabit.soundallaround.R
import ru.coolhabit.soundallaround.data.entity.ResultAlbums
import ru.coolhabit.soundallaround.databinding.AlbumItemBinding

class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val albumItemBinding = AlbumItemBinding.bind(itemView)
    private val name = albumItemBinding.name
    private val artist = albumItemBinding.artist
    private val date = albumItemBinding.date
    private val picture = albumItemBinding.picture
    private val style = albumItemBinding.style

    fun bind(resultAlbums: ResultAlbums) {
        name.text = resultAlbums.collectionName
        artist.text = resultAlbums.artistName
        date.text = editData(resultAlbums.releaseDate)
        style.text = resultAlbums.primaryGenreName

        if (resultAlbums.artworkUrl100.isEmpty()) {
            picture.setImageResource(R.drawable.white)
        } else {
            Picasso.get()
                .load(resultAlbums.artworkUrl100)
                .error(android.R.drawable.stat_notify_error)
                .into(picture)
        }
    }

    private fun editData(data: String): String {
        return data.dropLast(16)
    }
}