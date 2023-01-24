package com.example.service_audioplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.service_audioplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        val manager: FragmentManager = supportFragmentManager
        manager.beginTransaction()
            .add(R.id.cont_main, MainFragment(), "FIRST_FRAG")
            .commit()
    }
}