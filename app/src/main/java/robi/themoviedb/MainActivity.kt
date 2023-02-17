package robi.themoviedb

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import robi.themoviedb.databinding.ActivityMainBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var time_in_milli_seconds = 0L
    lateinit var countdown_timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.app_name)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        time_in_milli_seconds = 3 * 1000L
        countdown_timer = object : CountDownTimer(time_in_milli_seconds, 1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                startActivity(Intent(this@MainActivity, robi.themoviedb.ui.MainActivity::class.java))
            }

        }
        countdown_timer.start()
    }
}