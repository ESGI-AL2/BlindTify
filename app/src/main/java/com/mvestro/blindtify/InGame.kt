package com.mvestro.blindtify

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import com.mvestro.blindtify.Service.SpotifyService
import kotlinx.android.synthetic.main.activity_in_game.*

class InGame : AppCompatActivity() {

    var countDownTimer : CountDownTimer?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_game)
        this.setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE)


        startCounting()


    }

    private fun stopCounting() {
        countDownTimer!!.cancel()

    }

    private fun startCounting() {

        progressBarTimer.max = 30000 / 1000
        countDownTimer = object  : CountDownTimer(30000,1000){
            override fun onFinish() {
                stopCounting()
            }

            override fun onTick(millisUntilFinished: Long) {
                progressBarTimer.progress = Math.round(millisUntilFinished * 0.001f)
            }
        }.start()

        countDownTimer!!.start()
        SpotifyService.playUri("spotify:playlist:37i9dQZF1DX1X23oiQRTB5")
    }
}