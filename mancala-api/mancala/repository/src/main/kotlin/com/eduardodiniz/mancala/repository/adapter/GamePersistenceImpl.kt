package com.eduardodiniz.mancala.repository.adapter

import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.exception.GameNotFoundException
import com.eduardodiniz.mancala.repository.mapper.GameEntityMapper
import com.eduardodiniz.mancala.domain.repository.GamePersistence
import com.eduardodiniz.mancala.repository.repository.GameRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GamePersistenceImpl(
    private val gameRepository: GameRepository
) : GamePersistence {
    override fun save(game: Game): Game {
        val entity = GameEntityMapper.toEntity(game)
        val savedGame = gameRepository.save(entity)

        return GameEntityMapper.toDomain(savedGame)
    }

    override fun findById(id: UUID): Game {
        val game = gameRepository.findById(id).orElseThrow { throw GameNotFoundException("Game not found") }

        return GameEntityMapper.toDomain(game)
    }
}