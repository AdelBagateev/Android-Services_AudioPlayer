package com.example.service_audioplayer

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music (
    val id: Int,
    val name: String,
    val author: String,
    val year: String,
    @DrawableRes val cover: Int,
    @RawRes val raw : Int,
    var isPlaying:Boolean,
): Parcelable
