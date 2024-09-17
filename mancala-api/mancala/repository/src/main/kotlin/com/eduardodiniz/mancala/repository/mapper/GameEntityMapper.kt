package com.eduardodiniz.mancala.repository.mapper

import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.domain.enum.Status
import com.eduardodiniz.mancala.repository.entity.GameEntity

class GameEntityMapper {

    companion object {
        fun toEntity(game: Game) : GameEntity {
            return GameEntity(
                id = game.id,
                playersBoard = game.playersBoard.map { BoardEntityMapper.toEntity(it) },
                playerTurn = game.playerTurn.name,
                winner = game.winner?.name,
                status = game.status.name
            )
        }

        fun toDomain(gameEntity: GameEntity): Game {
            return Game(
                id = gameEntity.id,
                playersBoard = gameEntity.playersBoard.map { BoardEntityMapper.toDomain(it) },
                playerTurn = Player.valueOf(gameEntity.playerTurn),
                winner = gameEntity.winner?.let { Player.valueOf(it) },
                status = Status.valueOf(gameEntity.status)
            )
        }
    }

}