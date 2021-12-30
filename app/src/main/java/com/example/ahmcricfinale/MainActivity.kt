package com.example.ahmcricfinale

import android.content.Context
import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ahmcricfinale.R

class MainActivity : AppCompatActivity() {
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        constraintLayout = findViewById(R.id.constraintLayout)

        val display = windowManager.defaultDisplay
        val screenSize = Point()
        display.getRealSize(screenSize)
        val width = screenSize.x
        val height = screenSize.y

        Log.i("MainActivity", "Width: $width")
        Log.i("MainActivity", "Height: $height")


        when{
            width in 708..738 && height in 1542..1578-> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_720x1560)
                Log.i("MainActivity", "Image being used: splashscreen_720x1560")
            }
            width in 1075..1085 && height in 1915..1925-> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1080x1920)
                Log.i("MainActivity", "Image being used: splashscreen_1080x1920")
            }
            width in 1075..1085 && height in 2155..2165-> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1080x2160)
                Log.i("MainActivity", "Image being used: splashscreen_1080x2160")
            }
            width in 1075..1085 && height in 2215..2225 -> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1080x2220)
                Log.i("MainActivity", "Image being used: splashscreen_1080x2220")
            }
            width in 1075..1085 && height in 2275..2285-> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1080x2280)
                Log.i("MainActivity", "Image being used: splashscreen_1080x2280")
            }
            width in 1275..1285 && height in 1715..1925-> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1280x1920)
                Log.i("MainActivity", "Image being used: splashscreen_1280x1920")
            }
            width in 1435..1445 && height in 2555..2565 -> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1440x2560)
                Log.i("MainActivity", "Image being used: splashscreen_1440x2560")
            }
            width in 1435..1445 && height in 2875..2885 -> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1440x2880)
                Log.i("MainActivity", "Image being used: splashscreen_1440x2880")
            }
            width in 1435..1445 && height in 2955..2965 -> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1440x2960)
                Log.i("MainActivity", "Image being used: splashscreen_1440x2960")
            }
            width in 1435..1445 && height in 3035..3045 -> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1440x3040)
                Log.i("MainActivity", "Image being used: splashscreen_1440x3040")
            }
            else -> {
                constraintLayout.setBackgroundResource(R.drawable.splashscreen_1280x1920)
                Log.i("MainActivity", "Image being used: splashscreen_1280x1920")
            }
        }


        Handler().postDelayed({
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3500)

    }
}