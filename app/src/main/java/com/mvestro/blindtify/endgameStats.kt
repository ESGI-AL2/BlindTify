package com.mvestro.blindtify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_endgame_stats.*
import kotlinx.android.synthetic.main.activity_in_game.*
import kotlinx.android.synthetic.main.activity_players_names.*

class endgameStats : AppCompatActivity() {

    private var P1pts: String = ""
    private var P2pts: String = ""
    private var P3pts: String = ""
    private var P4pts: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endgame_stats)


        P1pts = intent.getStringExtra("P1pts").toString()
        P2pts = intent.getStringExtra("P2pts").toString()
        P3pts = intent.getStringExtra("P3pts").toString()
        P4pts = intent.getStringExtra("P4pts").toString()

        txtP1NameStats.text = intent.getStringExtra("P1Name")
        txtP2NameStats.text = intent.getStringExtra("P2Name")
        txtP3NameStats.text = intent.getStringExtra("P3Name")
        txtP4NameStats.text = intent.getStringExtra("P4Name")

        P1PtsStats.text = P1pts
        P2PtsStats.text = P2pts
        P3PtsStats.text = P3pts
        P4PtsStats.text = P4pts

        btnNewGame.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }

    }
}