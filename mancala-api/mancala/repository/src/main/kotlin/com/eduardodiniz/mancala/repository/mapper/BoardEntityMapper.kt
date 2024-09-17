package com.eduardodiniz.mancala.repository.mapper

import com.eduardodiniz.mancala.domain.domain.Board
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.repository.entity.BoardEntity

class BoardEntityMapper {

    companion object {
        fun toEntity(board: Board): BoardEntity {
            return BoardEntity(
                smallPits = board.pits.map { PitEntityMapper.toEntity(it) },
                bigPit = PitEntityMapper.toEntity(board.store),
                player = board.player.name
            )
        }

        fun toDomain(boardEntity: BoardEntity): Board {
            return Board(
                pits = boardEntity.smallPits.map { PitEntityMapper.toDomain(it) },
                store = PitEntityMapper.toDomain(boardEntity.bigPit),
                player = Player.valueOf(boardEntity.player)
            )
        }
    }

}