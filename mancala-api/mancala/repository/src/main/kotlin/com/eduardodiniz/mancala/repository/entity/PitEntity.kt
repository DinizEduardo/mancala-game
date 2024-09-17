package com.eduardodiniz.mancala.repository.entity

import org.springframework.data.redis.core.RedisHash

@RedisHash("PitEntity")
class PitEntity(
    val index: Int,
    val stones: Int
)