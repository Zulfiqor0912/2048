package uz.gita.b52048.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import uz.gita.b52048.R
import uz.gita.b52048.ui.home.HomeActivity

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private val h = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        h.postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            Animatoo.animateFade(this)
            finish()
        }, 1100)
    }
}