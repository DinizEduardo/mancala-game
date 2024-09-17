package com.eduardodiniz.mancala.domain.exception

import org.springframework.http.HttpStatus


class GameNotFoundException(message: String?) : ApiException(HttpStatus.NOT_FOUND, message)