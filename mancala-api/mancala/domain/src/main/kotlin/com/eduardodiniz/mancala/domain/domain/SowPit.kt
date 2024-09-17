package com.eduardodiniz.mancala.domain.domain

import com.eduardodiniz.mancala.domain.domain.enum.Player
import java.util.UUID

class SowPit(
    val gameId: UUID,
    val pitIndex: Int,
    val player: Player
) {
}