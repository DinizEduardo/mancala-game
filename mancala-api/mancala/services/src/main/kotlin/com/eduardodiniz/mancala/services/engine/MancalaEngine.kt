package com.eduardodiniz.mancala.services.engine

import com.eduardodiniz.mancala.domain.domain.Board
import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.Pit
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.domain.enum.Status
import com.eduardodiniz.mancala.domain.exception.InvalidMoveException
import com.eduardodiniz.mancala.domain.exception.PlayerNotFoundException


class MancalaEngine(
        private val game: Game,
        private val player: Player,
        private var playAgain: Boolean = false
) {

    companion object {
        fun createGame(): Game {
            return GameFactory.createGame()
        }

        fun play(game: Game, player: Player, pitIndex: Int): Game {
            if (game.playerTurn != player) {
                throw InvalidMoveException("It's not your turn")
            }
            return MancalaEngine(game, player).sow(player, pitIndex)
        }
    }

    private fun sow(player: Player, pitIndex: Int, amount: Int = 0): Game {
        val playerBoard = findPlayerBoard(player)
        val stonesToSow = if (amount == 0) playerBoard.getSmallPitByIndex(pitIndex).stones else amount

        if (amount == 0) playerBoard.getSmallPitByIndex(pitIndex).removeAllSeeds()

        var rest = sowSmallPitsPlayer(playerBoard, if (amount == 0) pitIndex+1 else 0, stonesToSow)

        rest = sowBigPit(playerBoard, rest)

        if (rest > 0) {
            val opponent = findPlayerBoard(player.opponent())
            rest = sowSmallPitsOpponentPlayer(opponent, -1, rest)

            if (rest > 0) {
                this.sow(player, 0, rest)
            }
        }

        this.game.playerTurn = if (this.playAgain) player else player.opponent()
        checkIfGameIsOver()

        return this.game
    }

    private fun sowBigPit(playerBoard: Board, stonesToSow: Int): Int {
        if (playerBoard.player == game.playerTurn && stonesToSow > 0) {
            playerBoard.store.addStone()
            if (stonesToSow == 1) {
                this.playAgain = true
            }
            return stonesToSow - 1
        }

        return stonesToSow
    }

    private fun findPlayerBoard(player: Player): Board {
        return game.playersBoard.firstOrNull { it.player == player } ?: throw PlayerNotFoundException("Player not found")
    }

    private fun isOutOfStones(playerBoard: Board): Boolean {
        return playerBoard.pits.stream().allMatch { it.stones == 0 }
    }

    private fun sowSmallPitsOpponentPlayer(playerBoard: Board, pitIndex: Int, stonesToSow: Int): Int {
        var auxStonesToSow = stonesToSow
        val pitsToSow = getPitsToSow(playerBoard, pitIndex)
        pitsToSow.stream().sorted(Comparator.comparingInt(Pit::index)).forEach {
            if (auxStonesToSow >= 1) {
                it.addStone()
                auxStonesToSow--
            }
        }

        return auxStonesToSow
    }

    private fun sowSmallPitsPlayer(playerBoard: Board, pitIndex: Int, stonesToSow: Int): Int {
        var auxStonesToSow = stonesToSow
        getPitsToSow(playerBoard, pitIndex).stream()
            .sorted(Comparator.comparingInt(Pit::index))
            .forEach { pit: Pit ->
                if (auxStonesToSow > 0) {
                    pit.addStone()
                    auxStonesToSow--
                    if (shouldCapture(pit, auxStonesToSow)) {
                        capture(playerBoard, pit)
                    }
                }
            }
        return auxStonesToSow
    }

    private fun getPitsToSow(playerBoard: Board, pitIndex: Int): List<Pit> {
        return playerBoard.pits
            .stream()
            .filter{ it.index >= pitIndex }
            .toList()
    }

    private fun shouldCapture(pit: Pit, stonesToSow: Int): Boolean {
        return pit.stones == 1 && getOppositePit(pit).stones > 0 && stonesToSow == 0
    }

    private fun capture(playerBoard: Board, pit: Pit) {
        val oppositePit = getOppositePit(pit)
        playerBoard.store.addStone(pit.stones + oppositePit.stones)
        pit.removeAllSeeds()
        oppositePit.removeAllSeeds()
    }

    private fun getOppositePit(pit: Pit): Pit {
        val opponentBoard = findPlayerBoard(player.opponent())
        val pitsInReverseOrder = opponentBoard.pits.stream()
                .sorted { pit1, pit2 -> Integer.compare(pit2.index, pit1.index) }
                .toList()

        return pitsInReverseOrder[pit.index]
    }

    private fun checkIfGameIsOver() {
        val player1Board = findPlayerBoard(Player.PLAYER_1)
        val player2Board = findPlayerBoard(Player.PLAYER_2)
        if (isOutOfStones(player1Board) || isOutOfStones(player2Board)) {
            this.game.status = Status.FINISHED
            val player1Stones = player1Board.pits.stream().mapToInt(Pit::stones).sum() + player1Board.store.stones
            val player2Stones = player2Board.pits.stream().mapToInt(Pit::stones).sum() + player2Board.store.stones

            defineWinner(player1Stones, player2Stones)
        }
    }

    private fun defineWinner(player1Stones: Int, player2Stones: Int) {
        if (player1Stones > player2Stones) {
            this.game.winner = Player.PLAYER_1
        } else if (player1Stones < player2Stones) {
            this.game.winner = Player.PLAYER_2
        }
    }

}