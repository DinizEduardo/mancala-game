package com.eduardodiniz.mancala.domain.service

import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.SowPit

interface GameService {

    fun createGame(): Game

    fun makeMove(sowPit: SowPit): Game

}