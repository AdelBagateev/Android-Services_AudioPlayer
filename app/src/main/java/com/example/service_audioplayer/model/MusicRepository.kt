package com.example.service_audioplayer.model

import com.example.service_audioplayer.Music
import com.example.service_audioplayer.R

object MusicRepository {
    val musics = arrayListOf(
        Music(
            id = 1,
            name = "Маг",
            author = "ЛСП",
            year = "2011",
            cover = R.drawable.img,
            raw = R.raw.mag,
            isPlaying = false
        ),
        Music(
            id = 2,
            name = "Хиппи",
            author = "ЛСП",
            year = "2011",
            cover = R.drawable.img,
            raw = R.raw.hippi,
            isPlaying = false
        ),
        Music(
            id = 3,
            name = "Улицы(Видеть цветные сны)",
            author = "ЛСП",
            year = "2011",
            cover = R.drawable.img,
            raw = R.raw.streats,
            isPlaying = false
        ),
        Music(
            id = 4,
            name = "Ламбада",
            author = "ЛСП",
            year = "2011",
            cover = R.drawable.img,
            raw = R.raw.lambada,
            isPlaying = false
        ),
        Music(
            id = 5,
            name = "Мир стал пустым без тебя",
            author = "ЛСП",
            year = "2011",
            cover = R.drawable.img,
            raw = R.raw.the_world_empty_without_you,
            isPlaying = false
        ),
        Music(
            id = 6,
            name = "Хиппи Aes Genius Remix",
            author = "ЛСП",
            year = "2011",
            cover = R.drawable.img,
            raw = R.raw.hippi_aes_genius_remix,
            isPlaying = false
        ),
    )

}