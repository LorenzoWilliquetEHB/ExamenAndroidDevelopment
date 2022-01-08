package be.ehb.cinema

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

// SplashScreen van 3 seconden met animatie en dan wordt de MainActivity geladen.

// Verdere info over een splashscreen
// https://developer.android.com/guide/topics/ui/splash-screen

// Is de video van waar ik deen voorbeeld van een splashscreen heb gevonden
// https://www.youtube.com/watch?v=WZWr0Abomfw&ab_channel=CodePalace

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        findViewById<ImageView>(R.id.cinema_logo).alpha = 0f
        findViewById<ImageView>(R.id.cinema_logo).animate().setDuration(3000).alpha(1f).withEndAction{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}