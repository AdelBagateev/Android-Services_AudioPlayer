package com.example.service_audioplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.os.Process
import android.util.Log
import com.example.service_audioplayer.databinding.FragmentMainBinding
import com.example.service_audioplayer.model.MusicRepository

class MediaService : Service() {
    private var mediaPlayer: MediaPlayer?=null
    private var prevId  :Int ?= null
    private val aidlBinder = object : PlayerAidlInterface.Stub() {
        override fun playMusic() {
            Log.e("HelloService.aidlBinder", "playLocaleMusic")
            playLocaleMusic()
        }




        override fun playNextMusic(music: Music?) {
            next(music)
        }
//        root.setBackgroundColor( Color.parseColor("#D7D7DF"))

        override fun playPreviousMusic(music: Music?) {

        }

        override fun stopMusic() {
            stop()
        }

        override fun pauseMusic() {
            pause()
        }
        override fun setMusic(music: Music?) {
             set(music)
        }
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onBind(intent: Intent): IBinder = aidlBinder
    private fun playLocaleMusic() {
    }


    private fun set(music : Music?) {
        music?.let {

//            mediaPlayer.


            if(prevId != music.id) mediaPlayer?.stop()

            if (mediaPlayer == null || prevId != music.id) {
                mediaPlayer = MediaPlayer.create(this, it.raw)
                mediaPlayer!!.isLooping = true
                mediaPlayer!!.start()
            } else mediaPlayer!!.start()
        }
        prevId = music?.id
    }
    private fun pause() {
        if (mediaPlayer?.isPlaying == true) mediaPlayer?.pause()
    }
    private fun stop() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer = null
        }
    }
    private fun next(music: Music?) {
//        var id : Int ?= music?.id
//
//        if(music?.id == 6) {
//            id = 0
//        }
//
//        val nextMusic : Music ?= MusicRepository.musics.find { it.id == id?.plus(1)}
//        set(nextMusic)
//    }
    }
}