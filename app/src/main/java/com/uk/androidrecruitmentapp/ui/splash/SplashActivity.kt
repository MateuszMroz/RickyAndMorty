package com.uk.androidrecruitmentapp.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.ui.MainActivity

class SplashActivity : Activity() {
    val sleep = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Launcher)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        // isn't perfect way - frosting main thread
        Thread {
            Thread.sleep(sleep)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }.run()
    }
}