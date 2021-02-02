package com.mvestro.blindtify

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.mvestro.blindtify.Service.SpotifyService
import kotlinx.android.synthetic.main.activity_in_game.*
import java.util.*
import java.util.function.DoubleConsumer
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask

class InGame : AppCompatActivity() {

    private var isPaused = false
    private var resumeFromMillis: Long = 0
    private var P1pts: Int = 0
    private var P2pts: Int = 0
    private var P3pts: Int = 0
    private var P4pts: Int = 0
    private var playerBuzz: Int = 0
    private var round: Int = 0
    private var P1Name: String = "P1"
    private var P2Name: String = "P2"
    private var P3Name: String = "P3"
    private var P4Name: String = "P4"

    var countDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_game)
        this.setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE)

        P1Name = intent.getStringExtra("P1Name").toString()
        P2Name = intent.getStringExtra("P2Name").toString()
        P3Name = intent.getStringExtra("P3Name").toString()
        P4Name = intent.getStringExtra("P4Name").toString()

        btnP1Buzz.text = P1Name
        btnP2Buzz.text = P2Name
        btnP3Buzz.text = P3Name
        btnP4Buzz.text = P4Name

        roundStart(0)
        txtRound.text = "Round : " + round


        btnP1Buzz.setOnClickListener {
            isPaused = true;
            viewRep.isVisible = true
            playerBuzz = 1
        }

        btnP2Buzz.setOnClickListener {
            isPaused = true;
            viewRep.isVisible = true
            playerBuzz = 2
        }

        btnP3Buzz.setOnClickListener {
            isPaused = true;
            viewRep.isVisible = true
            playerBuzz = 3
        }

        btnP4Buzz.setOnClickListener {
            isPaused = true;
            viewRep.isVisible = true
            playerBuzz = 4
        }

        btnRepBad.setOnClickListener {
            isPaused = false
            startCounting(resumeFromMillis)
            viewRep.isGone = true
        }

        btnRepGood.setOnClickListener {
            isPaused = false
            progressBarTimer.setProgress(0)
            viewRep.isGone = true
            val toast = Toast.makeText(applicationContext, "Bien joué !", Toast.LENGTH_SHORT)
            toast.show()
            roundWin(playerBuzz)
        }


    }

    private fun roundWin(player: Int) {
        stopCounting()
        when (player) {
            1 -> {
                P1pts++
                txtPtsP1.text = P1pts.toString()
            }
            2 -> {
                P2pts++
                txtPtsP2.text = P2pts.toString()
            }
            3 -> {
                P3pts++
                txtPtsP3.text = P3pts.toString()
            }
            4 -> {
                P4pts++
                txtPtsP4.text = P4pts.toString()
            }
            else -> playerBuzz = 0
        }
        round++
        resTop.text = "Artiste\nChanson"
        resBot.text = "Artiste\nChanson"
        txtRound.text = "Round : " + round
        Timer("new round", false).schedule(3000) {
            resTop.text = ""
            resBot.text = ""
        }
        if (round > 5) {
            roundStop()
        } else {
            roundStart(round)
        }
    }


    private fun roundStart(roundNb: Int) {
        round = roundNb
        playerBuzz = 0
        startCounting(15000)
    }

    private fun roundStop() {
        stopCounting()
        val intent = Intent(this, endgameStats::class.java)

        intent.putExtra("P1pts", P1pts.toString())
        intent.putExtra("P2pts", P2pts.toString())
        intent.putExtra("P3pts", P3pts.toString())
        intent.putExtra("P4pts", P4pts.toString())

        intent.putExtra("P1Name", P1Name)
        intent.putExtra("P2Name", P2Name)
        intent.putExtra("P3Name", P3Name)
        intent.putExtra("P4Name", P4Name)

        finish()
        startActivity(intent)
    }

    private fun stopCounting() {
        countDownTimer!!.cancel()
    }

    private fun startCounting(millisInFuture: Long, countDownInterval: Long = 1000) {

        progressBarTimer.max = (15000 / countDownInterval).toInt()
        countDownTimer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                val toast = Toast.makeText(applicationContext, "C'est fini !", Toast.LENGTH_LONG)
                roundWin(0)

            }

            override fun onTick(millisUntilFinished: Long) {
                progressBarTimer.progress = Math.round(millisUntilFinished * 0.001f)
                if (isPaused) {
                    resumeFromMillis = millisUntilFinished
                    stopCounting()
                }
            }
        }.start()

        countDownTimer!!.start()
        //SpotifyService.playUri("spotify:playlist:37i9dQZF1DX1X23oiQRTB5")
    }

}