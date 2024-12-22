package com.herdialfachri.lms_um_sukabumi.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.herdialfachri.lms_um_sukabumi.R

class GetStartedActivity : AppCompatActivity() {
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        startButton = findViewById(R.id.startButton)

        startButton.setOnClickListener {
            val intent = Intent(this@GetStartedActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}