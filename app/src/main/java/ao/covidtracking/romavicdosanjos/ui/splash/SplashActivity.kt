package ao.covidtracking.romavicdosanjos.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ao.covidtracking.romavicdosanjos.R
import ao.covidtracking.romavicdosanjos.data.api.ApiClient
import ao.covidtracking.romavicdosanjos.data.bridge.Bridge
import ao.covidtracking.romavicdosanjos.data.viewmodels.SplashViewModel
import ao.covidtracking.romavicdosanjos.data.viewmodels.factory.ViewModelFactory

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(
            this,
            ViewModelFactory(Bridge(ApiClient.RetrofitBuilder.endPoints))
        )[SplashViewModel::class.java]

    }

    override fun onStart() {
        super.onStart()
        splashViewModel.splashTimer(1200, this)
    }
}