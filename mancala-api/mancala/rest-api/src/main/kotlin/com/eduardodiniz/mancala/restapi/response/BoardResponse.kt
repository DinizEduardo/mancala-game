package com.eduardodiniz.mancala.restapi.response

data class BoardResponse(
        val smallPits: List<PitResponse>,
        val bigPit: PitResponse,
        val player: String
) {
}