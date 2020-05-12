package app.imuuzak.driving_management.ui.splash.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ActivitySplashBinding
import app.imuuzak.driving_management.ui.home.activity.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)

        Handler().postDelayed({
            toHome()
        }, 2000)
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
