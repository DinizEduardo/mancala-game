package com.eduardodiniz.mancala.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.UUID

@RedisHash("GameEntity")
data class GameEntity(
        @Id
    val id: UUID,
        val playersBoard: List<BoardEntity>,
        val playerTurn: String,
        val winner: String?,
        val status: String
)