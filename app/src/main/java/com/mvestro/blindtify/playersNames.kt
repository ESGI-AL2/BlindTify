package com.mvestro.blindtify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_players_names.*

class playersNames : AppCompatActivity() {

    private var P1Name: String = ""
    private var P2Name: String = ""
    private var P3Name: String = ""
    private var P4Name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_names)

        btnValidateNames.setOnClickListener {
            if (editTxtP1Name.text.toString() != "" && editTxtP2Name.text.toString() != "" && editTxtP3Name.text.toString() != "" && editTxtP4Name.text.toString() != "") {
                P1Name = editTxtP1Name.text.toString()
                P2Name = editTxtP2Name.text.toString()
                P3Name = editTxtP3Name.text.toString()
                P4Name = editTxtP4Name.text.toString()

                val intent = Intent(this, InGame::class.java)
                intent.putExtra("P1Name", P1Name)
                intent.putExtra("P2Name", P2Name)
                intent.putExtra("P3Name", P3Name)
                intent.putExtra("P4Name", P4Name)
                finish()
                startActivity(intent)
            } else {
                val toast = Toast.makeText(
                    applicationContext,
                    "Veuillez remplir tous les noms",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }
}