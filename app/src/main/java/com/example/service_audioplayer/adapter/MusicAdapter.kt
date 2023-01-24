package com.example.service_audioplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.service_audioplayer.databinding.ItemMusicBinding
import com.example.service_audioplayer.Music
import com.example.service_audioplayer.model.MusicRepository

class MusicAdapter (
    private val list: List<Music>,
    private val actionPlay: (Music) -> Unit,
    private val actionNavigate: (Music) -> Unit
) : RecyclerView.Adapter<MusicItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicItem = MusicItem(

        binding = ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
            false
        ),
        actionPlay = actionPlay,
        actionNavigate = actionNavigate
        )

    fun setList(newList: List<Music>) {
        DiffUtil.calculateDiff(UserDiffUtils(this.list, newList)).dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(
        holder: MusicItem,
        position: Int
    ) {

        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}