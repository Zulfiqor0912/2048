package uz.gita.b52048.ui.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import uz.gita.b52048.R
import uz.gita.b52048.constants.SideEnum
import uz.gita.b52048.databinding.ActivityGameBinding
import uz.gita.b52048.utils.BackgroundUtil
import uz.gita.b52048.utils.MyTouchListener

class GameActivity : AppCompatActivity(), GameContract.View {
    private lateinit var _binding: ActivityGameBinding
    private val binding get() = _binding
    private lateinit var items: MutableList<TextView>
    private lateinit var presenter: GamePresenter
    private lateinit var mainView: LinearLayoutCompat
    private lateinit var myTouchListener: MyTouchListener
    private val util = BackgroundUtil()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        loadView()
        describeMatrixToViews()
        val myTouchListener = MyTouchListener(this)
        myTouchListener.setMyMovementSideListener {
            when (it) {
                SideEnum.LEFT -> {
                    presenter.moveToLeft()
                    describeMatrixToViews()
                    val num = presenter.getBest()
                    val score = presenter.getScore()
                    binding.textBest.text = "$num"
                    binding.textScore.text = "$score"
                }
                SideEnum.RIGHT -> {
                    Log.d("FFF", "right")
                    presenter.moveToRight()
                    describeMatrixToViews()
                    val num = presenter.getBest()
                    binding.textBest.text = "$num"
                    val score = presenter.getScore()
                    binding.textScore.text = "$score"
                }
                SideEnum.DOWN -> {

                    Log.d("FFF", "down")
                    presenter.moveToDown()
                    describeMatrixToViews()
                    val num = presenter.getBest()
                    binding.textBest.text = "$num"
                    val score = presenter.getScore()
                    binding.textScore.text = "$score"
                }
                SideEnum.UP -> {
                    Log.d("FFF", "up")
                    presenter.moveToUp()
                    describeMatrixToViews()
                    val num = presenter.getBest()
                    binding.textBest.text = "$num"
                    val score = presenter.getScore()
                    binding.textScore.text = "$score"
                }
            }
        }
        mainView.setOnTouchListener(myTouchListener)

        binding.apply {
            buttonBefore.setOnClickListener {
                finish()
                Animatoo.animateSwipeRight(this@GameActivity);

            }

            buttonReset.setOnClickListener {
                presenter.clickRestartButton()
            }
        }
    }

    private fun loadView() {
        mainView = binding.mainView
        myTouchListener = MyTouchListener(this)
        for (i in 0 until mainView.childCount) {
            val linear = mainView.getChildAt(i) as LinearLayoutCompat
            for (j in 0 until linear.childCount) {
                items.add(linear.getChildAt(j) as TextView)
            }
        }
        val num = presenter.getBest()
        binding.textBest.text = "$num"
        val score = presenter.getScore()
        binding.textScore.text = "$score"
    }

    private fun loadData() {
        presenter = GamePresenter(this)
        items = ArrayList()
    }

    private fun describeMatrixToViews() {
        val matrix = presenter.loadMatrixToView()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                items[i * 4 + j].apply {
                    text = if (matrix[i][j] == 0) ""
                    else matrix[i][j].toString()
                    setBackgroundResource(util.colorByAmount(matrix[i][j]))
                }
            }
        }
    }

    override fun openRestartDialog() {
        val dialog = AlertDialog.Builder(this)
            .setView(R.layout.dialog_restart)
            .setCancelable(false)
            .show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<AppCompatButton>(R.id.button_yes)?.setOnClickListener {
            presenter.clickRestart()
            dialog.dismiss()
            loadView()
            describeMatrixToViews()
        }
        dialog.findViewById<AppCompatButton>(R.id.button_no)?.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun openGameOver() {
        val dialog = AlertDialog.Builder(this)
            .setView(R.layout.dialog_game_over)
            .setCancelable(false)
            .show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<AppCompatButton>(R.id.button_restart)?.setOnClickListener {
            presenter.clickRestart()
            dialog.dismiss()
            loadView()
            describeMatrixToViews()
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()
    }
}