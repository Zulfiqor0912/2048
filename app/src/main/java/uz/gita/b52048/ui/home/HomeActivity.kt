package uz.gita.b52048.ui.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import uz.gita.b52048.R
import uz.gita.b52048.databinding.ActivityHomeBinding
import uz.gita.b52048.ui.game.GameActivity

class HomeActivity : AppCompatActivity(), HomeContract.View {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        binding.apply {
            playButtonBg.setOnClickListener {
                presenter.clickPLayButton()
            }

            buttonInfo.setOnClickListener {
                presenter.clickInfoButton()
            }

            buttonBestScore.setOnClickListener {
                presenter.clickBestButton()
            }

            buttonRating.setOnClickListener {
                presenter.clickRatingButton()
            }
        }
    }

    private fun loadData() {
        presenter = HomePresenter(this)
    }

    override fun openGameActivity() {
        val intent = Intent(this@HomeActivity, GameActivity::class.java)
        startActivity(intent)

        Animatoo.animateSwipeLeft(this);
    }

    override fun openPlayMarketForRating() {
        val uri: Uri = Uri.parse("market://details?id=$packageName")
        val goToMarket: Intent = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }

    }

    override fun openInfoDialog() {
        val dialog = AlertDialog.Builder(this)
            .setView(R.layout.dialog_info)
            .setCancelable(false)
            .show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<AppCompatButton>(R.id.button_back)?.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun openBestScore(best: Int) {
        val dialog = AlertDialog.Builder(this)
            .setView(R.layout.dialog_best)
            .setCancelable(false)
            .show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<AppCompatTextView>(R.id.text_best)?.text = best.toString()
        dialog.findViewById<AppCompatButton>(R.id.button_back)?.setOnClickListener {
            dialog.dismiss()
        }
    }

}