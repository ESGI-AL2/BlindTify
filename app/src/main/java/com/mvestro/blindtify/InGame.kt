package com.mvestro.blindtify

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.mvestro.blindtify.Model.Game.Game
import com.mvestro.blindtify.Service.SpotifyService
import com.spotify.protocol.types.Track
import kotlinx.android.synthetic.main.activity_in_game.*
import java.util.*
import java.util.function.DoubleConsumer
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class InGame : AppCompatActivity() {

    private var isPaused = false
    private var resumeFromMillis: Long = 0
    private var playerBuzz: Int = 0
    private var round: Int = 0
    private var artistName: String = ""
    private var songName: String = ""


    var countDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_game)
        this.requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE

        btnP1Buzz.text = Game.P1Name
        btnP2Buzz.text = Game.P2Name
        btnP3Buzz.text = Game.P3Name
        btnP4Buzz.text = Game.P4Name


        roundStart(0)

        txtRound.text = getString(R.string.round, round)
        SpotifyService.playUri(Game.uri)
        SpotifyService.shuffle()
        getArtistName()
        getSongName()


        btnP1Buzz.setOnClickListener {
            buzz()
            playerBuzz = 1
        }

        btnP2Buzz.setOnClickListener {
            buzz()
            playerBuzz = 2
        }

        btnP3Buzz.setOnClickListener {
            buzz()
            playerBuzz = 3
        }

        btnP4Buzz.setOnClickListener {
            buzz()
            playerBuzz = 4
        }

        btnRepBad.setOnClickListener {
            isPaused = false
            progressBarTimer.setProgress(0)
            viewRep.isGone = true
            roundWin(0)
        }

        btnRepGood.setOnClickListener {
            isPaused = false
            progressBarTimer.setProgress(0)
            viewRep.isGone = true
            roundWin(playerBuzz)
        }


    }

    override fun onStop() {
        super.onStop()
        SpotifyService.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        SpotifyService.pause()
    }

    fun buzz(){
        isPaused = true;
        viewRep.isVisible = true
        rep.text = getSongName() + " - " + getArtistName()
    }

    override fun onPause() {
        super.onPause()
        isPaused = true
        SpotifyService.pause()
    }

    override fun onResume() {
        super.onResume()
        if(isPaused && viewRep.isGone != false){
            isPaused = false
            SpotifyService.resume()
            startCounting(resumeFromMillis)
        }
    }

    private fun roundWin(player: Int) {
        stopCounting()
        when (player) {
            1 -> {
                Game.P1pts++
                txtPtsP1.text = Game.P1pts.toString()
            }
            2 -> {
                Game.P2pts++
                txtPtsP2.text = Game.P2pts.toString()
            }
            3 -> {
                Game.P3pts++
                txtPtsP3.text = Game.P3pts.toString()
            }
            4 -> {
                Game.P4pts++
                txtPtsP4.text = Game.P4pts.toString()
            }
            else -> playerBuzz = 0
        }
        resTop.text = getSongName() + "\n" + getArtistName()
        resBot.text = getSongName() + "\n" + getArtistName()
        round++
        txtRound.text = getString(R.string.round, round)
        Run.after(3000, {
            resTop.text = ""
            resBot.text = ""
        })

        if (round > 5) {
            roundStop()
        } else {
            Run.after(3000, {
                SpotifyService.nextTrack()
                roundStart(round)
            })
        }
    }


    private fun roundStart(roundNb: Int) {
        round = roundNb
        playerBuzz = 0
        startCounting(15000)
    }

    private fun roundStop() {
        SpotifyService.pause()
        stopCounting()
        val intent = Intent(this, endgameStats::class.java)

        finish()
        startActivity(intent)
    }

    private fun stopCounting() {
        SpotifyService.pause()
        countDownTimer!!.cancel()
    }

    private fun startCounting(millisInFuture: Long, countDownInterval: Long = 1000) {
        progressBarTimer.max = (15000 / countDownInterval).toInt()
        countDownTimer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
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
    }

    private fun getSongName():String {
        SpotifyService.assertAppRemoteConnected()
            .playerApi
            .subscribeToPlayerState()
            .setEventCallback{
                songName = it.track.name
            }
        Log.i("musique", songName)

        return songName
    }

    private fun getArtistName(): String {
        SpotifyService.assertAppRemoteConnected()
            .playerApi
            .subscribeToPlayerState()
            .setEventCallback{
                artistName = it.track.artist.name
            }
        return artistName
    }

    class Run {
        companion object {
            fun after(delay: Long, process: () -> Unit){
                Handler().postDelayed({
                    process()
                }, delay)
            }
        }
    }

}