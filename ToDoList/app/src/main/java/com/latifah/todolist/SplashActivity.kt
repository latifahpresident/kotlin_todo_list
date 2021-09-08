package com.latifah.todolist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide() //hide action bar so that the splash screen takes up full height of length of the screen

        val i = Intent(this@SplashActivity, MainActivity::class.java) //run main activity after 3 sec

        Handler(Looper.getMainLooper()).postDelayed({ //delay the main activity for 3 sec (3000 milliseconds)
            startActivity(i)
        }, 3000) //3 sec in milliseconds
    }
}