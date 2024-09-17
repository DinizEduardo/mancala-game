package com.eduardodiniz.mancala.restapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.eduardodiniz.mancala.domain.service.GameService
import com.eduardodiniz.mancala.restapi.mapper.GameMapper
import com.eduardodiniz.mancala.restapi.mapper.SowPitMapper
import com.eduardodiniz.mancala.restapi.request.SowPitRequest

@RestController
@RequestMapping("/api/mancala/v1")
class GameController(private val gameService: GameService) {

    @PostMapping
    fun createGame() : ResponseEntity<Any> {
        val game = gameService.createGame()
        return ResponseEntity.status(HttpStatus.CREATED).body(GameMapper.toResponse(game))
    }

    @PutMapping
    fun makeMove(@RequestBody request: SowPitRequest) : ResponseEntity<Any> {
        val sow = SowPitMapper.toDomain(request)

        val game = gameService.makeMove(sow)

        return ResponseEntity.status(HttpStatus.OK).body(GameMapper.toResponse(game))
    }

}