package uz.gita.b52048.data.pref

import android.content.Context
import android.content.SharedPreferences
import uz.gita.b52048.utils.Constants.*

class LocalStorage private constructor(context: Context) {

    private var sharedPreferences: SharedPreferences

    private var editor: SharedPreferences.Editor


    init {
        sharedPreferences = context.getSharedPreferences("2048", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    companion object {
        private var localStorage: LocalStorage? = null

        fun init(context: Context) {
            if (localStorage == null) localStorage = LocalStorage(context)
        }

        fun getInstance(): LocalStorage {
            return localStorage!!
        }
    }

    fun saveBest(best: Int) {
        editor.putInt(BEST.string, best)
        editor.apply()
    }

    fun getBest() = sharedPreferences.getInt(BEST.string, 0)


    fun saveScore(score: Int) {
        editor.putInt(SCORE.string, score)
        editor.apply()
    }

    fun getScore() = sharedPreferences.getInt(SCORE.string, 0)

    fun saveMatrix(matrix: String) {
        editor.putString(MATRIX.string, matrix)
        editor.apply()
    }

    fun getMatrix() = sharedPreferences.getString(MATRIX.string, "")

}