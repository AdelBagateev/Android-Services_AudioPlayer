package com.example.service_audioplayer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.service_audioplayer.databinding.FragmentDetailInfoBinding
import com.example.service_audioplayer.model.MusicRepository.musics


class DetailInfoFragment : Fragment(R.layout.fragment_detail_info) {

    var binding : FragmentDetailInfoBinding ?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetailInfoBinding.bind(view)
        val musicID: Int = arguments?.getInt(ARG_NAME)!!
        val music : Music? =  musics.find {
            it.id == musicID
        }
        val sImages = music?.let { intArrayOf(it.cover) }
        binding?.run {
            tvTitle.text = (music?.author + "   -   " + music?.name)
            tvYear.text = "Год издания - " + music?.year
            tvNumberOfAlbom.text = "Номер трека В альбоме - " + music?.id
            music?.let { ivCover.setImageResource(it.cover) }

        }
    }




    companion object {
        private const val ARG_NAME = "id"
        fun newInstance(id: Int) = DetailInfoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_NAME, id)
            }
        }
    }
}

