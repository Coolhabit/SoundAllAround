package ru.coolhabit.soundallaround.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.coolhabit.soundallaround.R
import ru.coolhabit.soundallaround.data.entity.ResultAlbums
import ru.coolhabit.soundallaround.view.rv_viewholders.AlbumsViewHolder

class AlbumsListRecyclerAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items = mutableListOf<ResultAlbums>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AlbumsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AlbumsViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }

    fun addItems(list: List<ResultAlbums>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun click(resultAlbums: ResultAlbums)
    }
}