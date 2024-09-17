package com.eduardodiniz.mancala.restapi.response

data class GameResponse(
        val id: String,
        val playersBoard: List<BoardResponse>,
        val playerTurn: String,
        val winner: String?,
        val status: String
) {
}