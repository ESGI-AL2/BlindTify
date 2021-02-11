package com.mvestro.blindtify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvestro.blindtify.Model.Game
import kotlinx.android.synthetic.main.activity_endgame_stats.*
import kotlinx.android.synthetic.main.activity_in_game.*
import kotlinx.android.synthetic.main.activity_players_names.*

class endgameStats : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endgame_stats)

        txtP1NameStats.text = Game.P1Name
        txtP2NameStats.text = Game.P2Name
        txtP3NameStats.text = Game.P3Name
        txtP4NameStats.text = Game.P4Name

        P1PtsStats.text = Game.P1pts.toString()
        P2PtsStats.text = Game.P2pts.toString()
        P3PtsStats.text = Game.P3pts.toString()
        P4PtsStats.text = Game.P4pts.toString()

        btnNewGame.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }

    }
}