package com.eduardodiniz.mancala.domain.domain

import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.domain.enum.Status
import java.util.UUID

class Game(
        val id: UUID,
        val playersBoard: List<Board>,
        var playerTurn: Player,
        var winner: Player? = null,
        var status: Status = Status.IN_PROGRESS
) {

    fun getBoard(player: Player): Board {
        return playersBoard.first { it.player == player }
    }

}