package com.eduardodiniz.mancala.domain.exception

import org.springframework.http.HttpStatus


class InvalidMoveException(message: String?) : ApiException(HttpStatus.BAD_REQUEST, message)