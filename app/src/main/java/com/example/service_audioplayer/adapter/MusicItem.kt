package com.example.service_audioplayer.adapter

import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.service_audioplayer.databinding.ItemMusicBinding
import com.example.service_audioplayer.Music
import com.example.service_audioplayer.model.MusicRepository

class MusicItem(
    private val binding: ItemMusicBinding,
    private val actionPlay: (Music) -> Unit,
    private val actionNavigate: (Music) -> Unit
) :  RecyclerView.ViewHolder(binding.root) {
    private var music: Music? = null

    init {
        itemView.setOnClickListener {
            music?.also(actionNavigate)
        }
//         use only one [setOnClickListener]
    }

    fun onBind(music: Music) {
        with(binding) {
            tvName.text = music.name
            tvAuthor.text = music.author
//            Log.e(music.id.toString(), music.isPlaying.toString())
//            if (!music.isPlaying) {
//                Log.e( music.id.toString(), "done")
////                root.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
//            }
//            Log.e( music.id.toString(), music.isPlaying.toString())
            if (music.isPlaying)
                root.setBackgroundColor(Color.parseColor("#D7D7DF"))
            else
                root.setBackgroundColor(Color.parseColor("#FFFFFFFF"))

            root.setOnClickListener {
                actionNavigate(music)
            }
            btnPlay.setOnClickListener {

//                music.isPlaying = true
//                MusicRepository.musics.filter { it.id != music.id }.forEach { it.isPlaying = false }
//                root.setBackgroundColor(Color.parseColor("#D7D7DF"))
//                Log.e( music.id.toString(), root.background.toString())
//                MusicRepository.musics.forEach { Log.e("sss", it.id.toString()) }
                actionPlay(music)
            }
//            btnPause.setOnClickListener {
//                actionPause(music)
//            }
        }
    }
}
