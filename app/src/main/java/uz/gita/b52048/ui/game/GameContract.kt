package uz.gita.b52048.ui.game

interface GameContract {
    interface Model {
        fun getScore(): Int
        fun getBest(): Int
        fun getMoveLeftNumbers()
        fun getMoveRightNumbers()
        fun getMoveUpNumbers()
        fun getMoveDownNumbers()
        fun gameOver(): Boolean
        fun getMatrix(): Array<Array<Int>>
        fun saveMatrix(matrix: Array<Array<Int>>)
        fun restart()
    }

    interface Presenter {
        fun clickRestartButton()
        fun moveToLeft()
        fun moveToRight()
        fun moveToUp()
        fun moveToDown()
        fun loadMatrixToView(): Array<Array<Int>>
        fun saveMatrix()
        fun getBest(): Int
        fun getScore(): Int
        fun clickRestart()
    }

    interface View {
        fun openRestartDialog()
        fun openGameOver()
    }
}