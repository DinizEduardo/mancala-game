package com.eduardodiniz.mancala.services.engine

import com.eduardodiniz.mancala.domain.domain.Board
import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.domain.enum.Status
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameFactoryTest {

    @Test
    fun `should create a game with boards for both players`() {
        val game: Game = GameFactory.createGame()

        assertNotNull(game.id)
        assertEquals(Status.IN_PROGRESS, game.status)
        assertEquals(Player.PLAYER_1, game.playerTurn)
        assertNull(game.winner)
        assertEquals(2, game.playersBoard.size)
    }

    @Test
    fun `should create separate boards for both players`() {
        val game: Game = GameFactory.createGame()

        val boardPlayer1: Board = game.playersBoard.first { it.player == Player.PLAYER_1 }
        assertEquals(Player.PLAYER_1, boardPlayer1.player)
        assertEquals(6, boardPlayer1.pits.size)
        assertEquals(0, boardPlayer1.store.stones)

        val boardPlayer2: Board = game.playersBoard.first { it.player == Player.PLAYER_2 }
        assertEquals(Player.PLAYER_2, boardPlayer2.player)
        assertEquals(6, boardPlayer2.pits.size)
        assertEquals(0, boardPlayer2.store.stones)
    }

    @Test
    fun `should initialize each pit with 6 stones and store with 0`() {
        val game: Game = GameFactory.createGame()

        val boardPlayer1: Board = game.playersBoard.first { it.player == Player.PLAYER_1 }
        val boardPlayer2: Board = game.playersBoard.first { it.player == Player.PLAYER_2 }

        boardPlayer1.pits.forEach { pit ->
            assertEquals(6, pit.stones)
        }
        assertEquals(0, boardPlayer1.store.stones)

        boardPlayer2.pits.forEach { pit ->
            assertEquals(6, pit.stones)
        }
        assertEquals(0, boardPlayer2.store.stones)
    }
}