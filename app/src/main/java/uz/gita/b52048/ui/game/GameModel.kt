package uz.gita.b52048.ui.game

import uz.gita.b52048.data.repository.Repository

class GameModel : GameContract.Model {
    private var repository: Repository = Repository.instances()


    override fun getScore() = repository.getScore()


    override fun getBest() = repository.getBest()


    override fun getMoveLeftNumbers() = repository.left()


    override fun getMoveRightNumbers() = repository.right()


    override fun getMoveUpNumbers() = repository.up()


    override fun getMoveDownNumbers() = repository.down()


    override fun gameOver(): Boolean = repository.gameOver()


    override fun getMatrix(): Array<Array<Int>> = repository.matrix1

    override fun saveMatrix(matrix: Array<Array<Int>>) {

    }

    override fun restart() {
        repository.restart()
    }

}