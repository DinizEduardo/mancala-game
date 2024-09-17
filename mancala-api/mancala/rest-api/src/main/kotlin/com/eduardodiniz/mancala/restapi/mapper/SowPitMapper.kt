package com.eduardodiniz.mancala.restapi.mapper

import com.eduardodiniz.mancala.domain.domain.SowPit
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.restapi.request.SowPitRequest

class SowPitMapper {

    companion object {
        fun toDomain(request: SowPitRequest): SowPit {
            return SowPit(
                gameId = request.gameId,
                pitIndex = request.pitIndex,
                player = Player.valueOf(request.player)
            )
        }
    }

}