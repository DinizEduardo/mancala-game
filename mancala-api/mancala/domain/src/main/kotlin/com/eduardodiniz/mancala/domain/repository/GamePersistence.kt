package com.eduardodiniz.mancala.domain.repository

import com.eduardodiniz.mancala.domain.domain.Game
import java.util.*

interface GamePersistence {

    fun save(game: Game): Game

    fun findById(id: UUID): Game

}