package com.mvestro.blindtify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mvestro.blindtify.Service.SpotifyService

class MainActivity : AppCompatActivity() {

    companion object {
        private var mAccessToken: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.Game)

        button.setOnClickListener {
            val intent = Intent(this, PlaylistsList::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        SpotifyService.connectSDK(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mAccessToken = SpotifyService.connectAPI(requestCode, resultCode, data)
    }
}