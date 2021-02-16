package com.ibrahim.counttimetask

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        btStart.setOnClickListener {
            onStartCountButtonClicked()
        }
    }

    private fun onStartCountButtonClicked() {
        val seconds = etInput.text.toString().toIntOrNull()
        seconds ?: return

        startCountDown(seconds)

    }

    private fun startCountDown(seconds: Int) {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(seconds * 1000L, 1000) {
            override fun onFinish() {}

            override fun onTick(millisUntilFinished: Long) {
                displayFormattedRemainingTime((millisUntilFinished/1000).toInt())
            }
        }.start()
    }

    private fun displayFormattedRemainingTime(remainingTimeIsSeconds: Int) {
        val seconds: Int = remainingTimeIsSeconds % 60
        val minute: Int = (remainingTimeIsSeconds / 60) % 60
        val hours: Int = (remainingTimeIsSeconds / (60*60)) % 24
        val days: Int = (remainingTimeIsSeconds / (60*60*24))

        val formattedRemainingTime = String.format("%02d:%02d:%02d:%02d",  days, hours, minute, seconds)

        tvCount.text = formattedRemainingTime

    }
}