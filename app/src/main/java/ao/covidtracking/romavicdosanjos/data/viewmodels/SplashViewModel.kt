package ao.covidtracking.romavicdosanjos.data.viewmodels

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import ao.covidtracking.romavicdosanjos.ui.MainActivity
import java.util.*

class SplashViewModel : ViewModel() {

    fun splashTimer(timer: Long, activity: Activity) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                activity.startActivity(Intent(activity, MainActivity::class.java))
                activity.finish()
            }
        }, timer)
    }
}