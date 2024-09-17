package com.eduardodiniz.mancala.restapi.request

import java.util.UUID

data class SowPitRequest(
    var gameId: UUID,
    var pitIndex: Int,
    var player: String
)