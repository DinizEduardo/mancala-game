package com.eduardodiniz.mancala.repository.entity

import lombok.With
import org.springframework.data.redis.core.RedisHash

@RedisHash("BoardEntity")
@With
data class BoardEntity(
        val smallPits: List<PitEntity>,
        val bigPit: PitEntity,
        val player: String
)