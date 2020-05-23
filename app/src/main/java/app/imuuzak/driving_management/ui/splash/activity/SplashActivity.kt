package app.imuuzak.driving_management.ui.splash.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ActivitySplashBinding
import app.imuuzak.driving_management.ui.home.activity.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)

        val auth = FirebaseAuth.getInstance()
        auth.currentUser?.let {
            toHome()
        } ?: run {
            auth.signInAnonymously().addOnCompleteListener {
                if (it.isSuccessful) {
                    toHome()
                } else {
                    Toast.makeText(this@SplashActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                    print(it.exception?.message)
                }
            }
        }
    }

    private fun toHome() {
        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}
