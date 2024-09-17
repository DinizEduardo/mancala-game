package com.eduardodiniz.mancala.services.engine

import com.eduardodiniz.mancala.domain.domain.Board
import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.Pit
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.domain.enum.Status
import java.util.UUID

class GameFactory {

    companion object {
        fun createGame(): Game {
            return Game(
                UUID.randomUUID(),
                createPlayersBoard(),
                Player.PLAYER_1,
                null,
                Status.IN_PROGRESS
            )
        }

        private fun createPlayersBoard(): List<Board> {
            val player1Board = createBoard(Player.PLAYER_1)
            val player2Board = createBoard(Player.PLAYER_2)
            return listOf(player1Board, player2Board)
        }

        private fun createBoard(player: Player): Board {
            return Board(
                listOf(
                    Pit(0, 6),
                    Pit(1, 6),
                    Pit(2, 6),
                    Pit(3, 6),
                    Pit(4, 6),
                    Pit(5, 6)
                ),
                Pit(6, 0),
                player
            )
        }

    }


}