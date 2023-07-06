package uz.gita.b52048.data.repository

import android.util.Log
import uz.gita.b52048.data.pref.LocalStorage
import kotlin.random.Random

class Repository private constructor() {

    private val myPref = LocalStorage.getInstance()
    private var score: Int = 0

    companion object {
        private lateinit var repository: Repository

        fun instances(): Repository {
            if (!(Companion::repository.isInitialized))
                repository = Repository()
            return repository
        }
    }

    private val newElement = 2

    fun restart() {
        matrix1 = arrayOf(
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0)
        )
        score = 0
        saveScore(score)
        addNewElement()
        addNewElement()
    }


    var matrix1 = arrayOf(
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0)
    )

    init {
        addNewElement()
        addNewElement()
    }

    private fun addNewElement() {
        val list = ArrayList<Pair<Int, Int>>()
        for (i in matrix1.indices) {
            for (j in matrix1[i].indices) {
                if (matrix1[i][j] == 0) list.add(Pair(i, j))
            }
        }

        if (list.size > 0) {
            val randomPos = Random.nextInt(0, list.size)
            matrix1[list[randomPos].first][list[randomPos].second] = newElement
        }
        saveScore(score)
        if (getBest() <= getScore()) saveBest(getScore())
    }

    fun left() {
        Log.d("TTT", "left")
        for (i in matrix1.indices) {
            val amount = ArrayList<Int>(4)
            var bool = true
            for (j in matrix1[i].indices) {
                if (matrix1[i][j] == 0) continue
                if (amount.isEmpty()) amount.add(matrix1[i][j])
                else {
                    if (amount.last() == matrix1[i][j] && bool) {
                        score = myPref.getScore() + matrix1[i][j] * 2
                        myPref.saveScore(score)
                        Log.d("SSS", "$score")
                        amount[amount.size - 1] = amount.last() * 2
                        bool = false
                    } else {
                        amount.add(matrix1[i][j])
                        bool = true
                    }
                }
                matrix1[i][j] = 0
            }
            for (k in amount.indices) {
                matrix1[i][k] = amount[k]
            }
        }
        if (!gameOver()) {
            addNewElement()
        }
    }

    fun right() {
        Log.d("TTT", "right")
        for (i in matrix1.indices) {
            val amounts = ArrayList<Int>(4)
            var bool = true
            for (j in matrix1[i].size - 1 downTo 0) {
                if (matrix1[i][j] == 0) continue
                if (amounts.isEmpty()) amounts.add(matrix1[i][j])
                else {
                    if (amounts.last() == matrix1[i][j] && bool) {
                        score = myPref.getScore() + matrix1[i][j] * 2
                        myPref.saveScore(score)
                        Log.d("SSS", "$score")
                        amounts[amounts.size - 1] = amounts.last() * 2
                        bool = false
                    } else {
                        amounts.add(matrix1[i][j])
                        bool = true
                    }
                }
                matrix1[i][j] = 0
            }

            for (k in amounts.indices) {
                matrix1[i][matrix1[i].size - k - 1] = amounts[k]
            }
        }
        if (!gameOver()) {
            addNewElement()
        }
    }

    fun down() {
        Log.d("TTT", "down")
        for (i in matrix1.indices) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in matrix1[i].indices) {
                if (matrix1[j][i] == 0) continue
                if (amount.isEmpty()) amount.add(matrix1[j][i])
                else {
                    if (amount.last() == matrix1[j][i] && bool) {
                        score = myPref.getScore() + matrix1[i][j] * 2
                        myPref.saveScore(score)
                        Log.d("SSS", "$score")
                        amount[amount.size - 1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix1[j][i])
                    }
                }
                matrix1[j][i] = 0
            }
            for (j in 0 until amount.size) {
                matrix1[j][i] = amount[j]
            }
        }
        if (!gameOver()) {
            addNewElement()
        }
    }

    fun up() {
        Log.d("TTT", "up")
        for (i in matrix1.indices) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in matrix1[i].size - 1 downTo 0) {
                if (matrix1[j][i] == 0) continue
                if (amount.isEmpty()) amount.add(matrix1[j][i])
                else {
                    if (amount.last() == matrix1[j][i] && bool) {
                        score = myPref.getScore() + matrix1[i][j] * 2
                        myPref.saveScore(score)
                        Log.d("SSS", "$score")
                        amount[amount.size - 1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix1[j][i])
                    }
                }
                matrix1[j][i] = 0
            }
            for (j in amount.size - 1 downTo 0) {
                matrix1[3 - j][i] = amount[j]
            }
        }
        if (!gameOver()) {
            addNewElement()
        }
    }


    fun gameOver(): Boolean {
        if (moveToVertical()) Log.d("TTT", "Vertical TRUE")
        else Log.d("TTT", "Vertical FALSE")

        if (moveToHorizontal()) Log.d("TTT", "Horizontal TRUE")
        else Log.d("TTT", "Horizontal FALSE")

        return !moveToHorizontal() && !moveToVertical() && !isEmptyNumMatrix(matrix1)
    }


    private fun moveToVertical(): Boolean {
        for (i in 0 until matrix1.size - 1) {
            for (j in matrix1[i].indices) {
                if ((matrix1[i][j] == matrix1[i + 1][j]) || (matrix1[i + 1][j] == 0)) return true
            }
        }
        return false
    }

    private fun moveToHorizontal(): Boolean {
        for (i in matrix1.indices) {
            for (j in 0 until matrix1[i].size - 1) {
                if ((matrix1[i][j] == matrix1[i][j + 1]) || (matrix1[i][j + 1] == 0)) return true
            }
        }
        return false
    }

    fun saveBest(best: Int) = myPref.saveBest(best)


    fun getBest() = myPref.getBest()


    fun saveScore(score: Int) = myPref.saveScore(score)


    fun getScore() = myPref.getScore()

    fun getMatrix(): Array<Array<Int>> {
        return stringToMatrix(myPref.getMatrix()!!)
    }

    fun saveMatrix(matrix: Array<Array<Int>>) {
        myPref.saveMatrix(matrixToString(matrix))
    }

    private fun matrixToString(matrix: Array<Array<Int>>): String {
        val stringBuilder = StringBuilder()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                stringBuilder.append(matrix[i][j].toString()).append("#")
            }
        }
        return stringBuilder.toString()
    }

    private fun stringToMatrix(string: String): Array<Array<Int>> {
        val array = string.split("#")
        val myArray = Array(4) { Array(4) { 0 } }
        for (i in myArray.indices) {
            for (j in 0 until myArray[i].size) {
                myArray[i][j] = array[i * 4 + j].toIntOrNull()!!
            }
        }
        return myArray
    }

    fun isEmptyNumMatrix(matrix: Array<Array<Int>>): Boolean {
        for (i in matrix1.indices)
            for (j in 0 until matrix1[i].size) if (matrix[i][j] == 0) return true
        return false
    }
}