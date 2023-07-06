package uz.gita.b52048.ui.home

interface HomeContract {
    interface Model {
        fun getRecord():Int
    }

    interface View {
        fun openGameActivity()
        fun openPlayMarketForRating()
        fun openInfoDialog()
        fun openBestScore(best:Int)
    }

    interface Presenter {
        fun clickPLayButton()
        fun clickInfoButton()
        fun clickRatingButton()
        fun clickBestButton()
    }
}