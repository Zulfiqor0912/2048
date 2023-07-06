package uz.gita.b52048.ui.game

class GamePresenter(private val view: GameContract.View) : GameContract.Presenter {
    private var model: GameContract.Model = GameModel()

    override fun clickRestartButton() {
        view.openRestartDialog()
    }

    override fun moveToLeft() {
        model.getMoveLeftNumbers()
        if (model.gameOver()) view.openGameOver()
    }

    override fun moveToRight() {
        model.getMoveRightNumbers()
        if (model.gameOver()) view.openGameOver()

    }

    override fun moveToUp() {
        model.getMoveUpNumbers()
        if (model.gameOver()) view.openGameOver()
    }

    override fun moveToDown() {
        model.getMoveDownNumbers()
        if (model.gameOver()) view.openGameOver()
    }

    override fun loadMatrixToView(): Array<Array<Int>> {
        return model.getMatrix()
    }


    override fun saveMatrix() {

    }

    override fun getBest() = model.getBest()

    override fun getScore() = model.getScore()

    override fun clickRestart() {
        model.restart()
    }
}