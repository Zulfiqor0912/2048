package uz.gita.b52048.ui.home

import uz.gita.b52048.ui.game.GameContract
import uz.gita.b52048.ui.game.GameModel

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {
    private val model: GameContract.Model = GameModel()

    override fun clickPLayButton() {
        view.openGameActivity()
    }

    override fun clickInfoButton() {
        view.openInfoDialog()
    }

    override fun clickRatingButton() {
        view.openPlayMarketForRating()
    }

    override fun clickBestButton() {
        view.openBestScore(model.getBest())
    }
}