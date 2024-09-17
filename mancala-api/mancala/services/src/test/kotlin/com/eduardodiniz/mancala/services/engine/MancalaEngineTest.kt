package com.eduardodiniz.mancala.services.engine

import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.domain.enum.Status
import com.eduardodiniz.mancala.domain.exception.InvalidMoveException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class MancalaEngineTest {

    @Test
    fun `should create a new game`() {
        val game: Game = MancalaEngine.createGame()

        assertNotNull(game.id)
        assertEquals(Status.IN_PROGRESS, game.status)
        assertEquals(Player.PLAYER_1, game.playerTurn)
        assertEquals(2, game.playersBoard.size)
    }

    @Test
    fun `should allow valid move for current player`() {
        val game = MancalaEngine.createGame()

        val updatedGame = MancalaEngine.play(game, Player.PLAYER_1, 4)

        assertEquals(Player.PLAYER_2, updatedGame.playerTurn)
    }

    @Test
    fun `should throw exception if player plays out of turn`() {
        val game = MancalaEngine.createGame()

        val exception = assertThrows<InvalidMoveException> {
            MancalaEngine.play(game, Player.PLAYER_2, 0)
        }

        assertEquals("It's not your turn", exception.message)
    }

    @Test
    fun `should end game when one player runs out of stones  and declare winner 2`() {
        val game = MancalaEngine.createGame()

        game.playersBoard[0].pits.forEach { it.removeAllSeeds() }
        game.playersBoard[0].pits[5].stones = 1
        game.playersBoard[1].pits[0].stones = 1

        val updatedGame = MancalaEngine.play(game, Player.PLAYER_1, 5)

        assertEquals(Status.FINISHED, updatedGame.status)
        assertEquals(Player.PLAYER_2, updatedGame.winner)
    }

    @Test
    fun `should end game when one player runs out of stones and declare winner 1`() {
        val game = MancalaEngine.createGame()
        game.playerTurn = Player.PLAYER_2
        game.playersBoard[1].pits.forEach { it.removeAllSeeds() }
        game.playersBoard[1].pits[5].stones = 1
        game.playersBoard[0].pits[0].stones = 1

        val updatedGame = MancalaEngine.play(game, Player.PLAYER_2, 5)

        assertEquals(Status.FINISHED, updatedGame.status)
        assertEquals(Player.PLAYER_1, updatedGame.winner)
    }

    @Test
    fun `should capture opponent stones when landing in empty pit`() {
        val game = MancalaEngine.createGame()

        game.playersBoard[0].pits[0].stones = 1
        game.playersBoard[0].pits[1].stones = 0
        game.playersBoard[1].pits[4].stones = 3

        val updatedGame = MancalaEngine.play(game, Player.PLAYER_1, 0)

        assertEquals(4, updatedGame.playersBoard[0].store.stones)

        assertEquals(0, updatedGame.playersBoard[1].pits[4].stones)
    }

    @Test
    fun `should allow player to play again when ending in own store`() {
        val game = MancalaEngine.createGame()

        game.playersBoard[0].pits[5].stones = 1

        val updatedGame = MancalaEngine.play(game, Player.PLAYER_1, 0)

        assertEquals(Player.PLAYER_1, updatedGame.playerTurn)
    }

    @Test
    fun `should allow the game to make a full loop capturing`() {
        val game = MancalaEngine.createGame()

        game.playersBoard[0].pits[0].stones = 13

        val updatedGame = MancalaEngine.play(game, Player.PLAYER_1, 0)

        assertEquals(Player.PLAYER_2, updatedGame.playerTurn)
        assertEquals(game.playersBoard[0].pits[0].stones, 0)
        assertEquals(game.playersBoard[1].pits[5].stones, 0)

        assertEquals(game.playersBoard[0].store.stones, 9)
    }

}