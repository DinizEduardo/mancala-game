package com.eduardodiniz.mancala.repository.repository

import com.eduardodiniz.mancala.repository.entity.GameEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface GameRepository : CrudRepository<GameEntity, UUID> {}