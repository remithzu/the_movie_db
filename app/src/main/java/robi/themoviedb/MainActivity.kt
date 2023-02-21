package robi.themoviedb

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import robi.themoviedb.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timeInMilliSeconds = 0L
    private lateinit var countdownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.app_name)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        timeInMilliSeconds = 3 * 1000L
        countdownTimer = object : CountDownTimer(timeInMilliSeconds, 1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                startActivity(Intent(this@MainActivity, robi.themoviedb.ui.MainActivity::class.java))
            }

        }
        countdownTimer.start()
    }
}