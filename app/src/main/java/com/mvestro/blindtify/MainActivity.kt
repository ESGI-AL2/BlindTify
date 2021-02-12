package com.mvestro.blindtify

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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


        if (isNetworkConnected()) {
            button.setOnClickListener {
                val intent = Intent(this, PlaylistsList::class.java)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, R.string.ConnectezAvantJouer, Toast.LENGTH_LONG)
                .show()
            finishAffinity()
        }

    }

    override fun onStart() {
        super.onStart()
        SpotifyService.connectSDK(this, applicationContext)
    }

    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mAccessToken = SpotifyService.connectAPI(requestCode, resultCode, data)
    }
}