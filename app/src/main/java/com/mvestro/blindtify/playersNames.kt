package com.mvestro.blindtify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mvestro.blindtify.Model.Game.Game
import kotlinx.android.synthetic.main.activity_players_names.*

class playersNames : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_names)

        //if(ingame.isNetworkConnected()){
            btnValidateNames.setOnClickListener {
                if (editTxtP1Name.text.toString() != "" && editTxtP2Name.text.toString() != "" && editTxtP3Name.text.toString() != "" && editTxtP4Name.text.toString() != "") {
                    Game.P1Name = editTxtP1Name.text.toString()
                    Game.P2Name = editTxtP2Name.text.toString()
                    Game.P3Name = editTxtP3Name.text.toString()
                    Game.P4Name = editTxtP4Name.text.toString()

                    val intent = Intent(this, InGame::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    val toast = Toast.makeText(
                        applicationContext,
                        R.string.veuillez_remplir_tous_les_noms,
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        /*} else {
            Toast.makeText(this, "R.string.ConnectezAvantJouer", Toast.LENGTH_LONG)
                .show()
        }*/
    }
}