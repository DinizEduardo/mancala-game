package com.eduardodiniz.mancala.restapi.mapper

import com.eduardodiniz.mancala.domain.domain.Board
import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.Pit
import com.eduardodiniz.mancala.restapi.response.BoardResponse
import com.eduardodiniz.mancala.restapi.response.GameResponse
import com.eduardodiniz.mancala.restapi.response.PitResponse

class GameMapper {

    companion object {
        fun toResponse(game: Game): GameResponse {
            return GameResponse(
                id = game.id.toString(),
                playersBoard = game.playersBoard.map { it.toResponse() },
                playerTurn = game.playerTurn.name,
                winner = game.winner?.name,
                status = game.status.name
            )
        }
    }

}

private fun Board.toResponse() = BoardResponse(
    smallPits = pits.map { it.toResponse() },
    bigPit = store.toResponse(),
    player = player.toString()
)

private fun Pit.toResponse() = PitResponse(
    index = index,
    stones = stones
)