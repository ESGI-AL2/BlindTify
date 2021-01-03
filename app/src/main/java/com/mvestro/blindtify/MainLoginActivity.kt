package com.mvestro.blindtify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainLoginActivity : AppCompatActivity() {

        lateinit var boutonChanger : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        boutonChanger = findViewById(R.id.Play)

        val monIntent =  Intent(this,PartyActivity::class.java)

        boutonChanger.setOnClickListener{
            startActivity(monIntent)
        }
    }
}