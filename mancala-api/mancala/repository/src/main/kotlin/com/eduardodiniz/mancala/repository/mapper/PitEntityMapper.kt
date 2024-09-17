package com.eduardodiniz.mancala.repository.mapper

import com.eduardodiniz.mancala.domain.domain.Pit
import com.eduardodiniz.mancala.repository.entity.PitEntity

class PitEntityMapper {

    companion object {
        fun toEntity(pit: Pit): PitEntity {
            return PitEntity(
                index = pit.index,
                stones = pit.stones
            )
        }

        fun toDomain(pitEntity: PitEntity): Pit {
            return Pit(
                index = pitEntity.index,
                stones = pitEntity.stones
            )
        }
    }

}