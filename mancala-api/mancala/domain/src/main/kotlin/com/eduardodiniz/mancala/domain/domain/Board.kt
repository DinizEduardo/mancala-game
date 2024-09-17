package com.eduardodiniz.mancala.domain.domain

import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.exception.PitNotFoundException

class Board(
        val pits: List<Pit>,
        val store: Pit,
        val player: Player
) {

    fun getSmallPitByIndex(index: Int): Pit {
        return pits.firstOrNull { it.index == index } ?: throw PitNotFoundException("Pit not found")
    }

}