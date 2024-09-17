package com.eduardodiniz.mancala.services.services

import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.SowPit
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.repository.GamePersistence
import com.eduardodiniz.mancala.services.engine.MancalaEngine
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.UUID

class GameServiceImplTest {

    private val persistence: GamePersistence = mockk()
    private val gameService = GameServiceImpl(persistence)

    @Test
    fun `should create and persist a new game`() {
        val newGame = MancalaEngine.createGame()
        every { persistence.save(any<Game>()) } returns newGame

        val createdGame = gameService.createGame()

        assertNotNull(createdGame)
        assertEquals(newGame, createdGame)
        verify { persistence.save(any<Game>()) }
    }

    @Test
    fun `should retrieve game, perform move, and persist the result`() {
        val gameId = UUID.randomUUID()
        val pitIndex = 0
        val player = Player.PLAYER_1
        val sowPit = SowPit(gameId, pitIndex, player)

        val existingGame = MancalaEngine.createGame()
        every { persistence.findById(gameId) } returns existingGame

        val updatedGame = MancalaEngine.play(existingGame, player, pitIndex)
        every { persistence.save(any<Game>()) } returns updatedGame

        val result = gameService.makeMove(sowPit)

        assertEquals(updatedGame, result)
        verify { persistence.findById(gameId) }
        verify { persistence.save(updatedGame) }
    }

}