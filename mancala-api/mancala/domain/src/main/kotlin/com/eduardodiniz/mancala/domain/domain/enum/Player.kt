package com.eduardodiniz.mancala.domain.domain.enum

enum class Player {
    PLAYER_1,
    PLAYER_2;

    fun opponent(): Player {
        return when (this) {
            PLAYER_1 -> PLAYER_2
            PLAYER_2 -> PLAYER_1
        }
    }
}