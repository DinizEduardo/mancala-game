package com.eduardodiniz.mancala.services.services

import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.SowPit
import com.eduardodiniz.mancala.services.engine.MancalaEngine
import org.springframework.stereotype.Service
import com.eduardodiniz.mancala.domain.repository.GamePersistence
import com.eduardodiniz.mancala.domain.service.GameService

@Service
class GameServiceImpl(
    private val persistence: GamePersistence
) : GameService {

    override fun createGame(): Game {
        return persistence.save(MancalaEngine.createGame())
    }

    override fun makeMove(sowPit: SowPit): Game {
        val game = persistence.findById(sowPit.gameId)
        val result = MancalaEngine.play(game, sowPit.player, sowPit.pitIndex)
        return persistence.save(result)
    }

}