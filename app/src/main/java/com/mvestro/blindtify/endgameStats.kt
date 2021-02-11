package com.mvestro.blindtify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_endgame_stats.*
import kotlinx.android.synthetic.main.activity_in_game.*
import kotlinx.android.synthetic.main.activity_players_names.*

class endgameStats : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endgame_stats)

        txtP1NameStats.text = com.mvestro.blindtify.Model.Game.P1Name
        txtP2NameStats.text = com.mvestro.blindtify.Model.Game.P2Name
        txtP3NameStats.text = com.mvestro.blindtify.Model.Game.P3Name
        txtP4NameStats.text = com.mvestro.blindtify.Model.Game.P4Name

        P1PtsStats.text = com.mvestro.blindtify.Model.Game.P1pts.toString()
        P2PtsStats.text = com.mvestro.blindtify.Model.Game.P2pts.toString()
        P3PtsStats.text = com.mvestro.blindtify.Model.Game.P3pts.toString()
        P4PtsStats.text = com.mvestro.blindtify.Model.Game.P4pts.toString()

        btnNewGame.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }

    }
}