package com.example.service_audioplayer

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.service_audioplayer.adapter.MusicAdapter
import com.example.service_audioplayer.adapter.SpaceItemDecorator
import com.example.service_audioplayer.databinding.FragmentMainBinding
import com.example.service_audioplayer.model.MusicRepository

class MainFragment : Fragment(R.layout.fragment_main) {
    private var binding : FragmentMainBinding?= null
    private var adapter : MusicAdapter?= null
    private var binder: PlayerAidlInterface ?= null
    private var title:String = ""
    private var musicIsPlaying: Music ?= null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = PlayerAidlInterface.Stub.asInterface(service)
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unbindService(connection)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.bindService(
            Intent(activity, MediaService::class.java),
            connection,
            Service.BIND_AUTO_CREATE
        )
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding?.run {

            ivMusicOf.setOnClickListener {
                musicIsPlaying =  MusicRepository.musics.findLast { music -> music.isPlaying}
                musicIsPlaying?.apply {
                    stopMusic(this)
                }
            }

            ivPrevious.setOnClickListener {
                musicIsPlaying =  MusicRepository.musics.findLast { music -> music.isPlaying}
                musicIsPlaying?.apply {
                    previousMusic(this)
                }
            }


            ivPlay.setOnClickListener {
                musicIsPlaying?.apply{playMusic(this)}

            }

            ivNext.setOnClickListener {
                musicIsPlaying =  MusicRepository.musics.findLast { music -> music.isPlaying}
                musicIsPlaying?.apply {
                    nextMusic(this)
                }
            }

            ivPause.setOnClickListener {
                musicIsPlaying =  MusicRepository.musics.findLast { music -> music.isPlaying}
                musicIsPlaying?.apply { pauseMusic(this) }
            }




            val itemDecoration = context?.let {
                SpaceItemDecorator(
                    it,
                    16.0f
                )
            }

            MusicRepository.musics.find { it.isPlaying }?.let { tvTitle.text = (it.author + "   -   " + it.name) }




            adapter = MusicAdapter(
                list =  MusicRepository.musics,
                actionPlay =  {
                    playMusic(it)
                },
                actionNavigate =  {
                    parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                    )
                    .replace(R.id.cont_main, DetailInfoFragment.newInstance(it.id))
                    .addToBackStack("list")
                    .commit()
            },
            )








//            rvMusics.setOnClickListener {
////                binder?.setMusic()
//            }
            rvMusics.adapter = adapter
            rvMusics.layoutManager = LinearLayoutManager(context)
            rvMusics.addItemDecoration(itemDecoration!!)
        }
    }



    fun stopMusic(music: Music) {
        music.isPlaying = false
        adapter?.setList(MusicRepository.musics)
        adapter?.notifyDataSetChanged()
        binding?.tvTitle?.text = ""
        binder?.stopMusic()

    }


    fun pauseMusic(music : Music) {
        music.isPlaying = false
        adapter?.setList(MusicRepository.musics)
        adapter?.notifyDataSetChanged()
        binding?.tvTitle?.text = ""
        binder?.pauseMusic()
    }


    fun nextMusic(music: Music) {
        music.apply {
            var id: Int? = this.id
            if (this.id == 6) {
                id = 0
            }
            val nextMusic: Music? = MusicRepository.musics.find { it.id == id?.plus(1) }
//            music.isPlaying = false
//            nextMusic?.isPlaying = true
            musicIsPlaying = nextMusic
            nextMusic?.apply { playMusic(this) }
        }
    }

    fun previousMusic(music: Music) {
        music.apply {
            var id: Int? = this.id
            if (this.id == 1) {
                id = 7
            }
            val previousMusic: Music? = MusicRepository.musics.find { it.id == id?.minus(1) }
            musicIsPlaying = previousMusic
            previousMusic?.apply { playMusic(this) }
        }
    }




    fun playMusic(music: Music) {
        binder?.setMusic(music)
        music.isPlaying = true
        MusicRepository.musics.filter {mus ->  mus.id != music.id }.forEach {mus ->  mus.isPlaying = false }
        adapter?.setList(MusicRepository.musics)
        adapter?.notifyDataSetChanged()


        title = (music.author + "   -   " + music.name)
        binding?.tvTitle?.text = title
    }
}