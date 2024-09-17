package com.eduardodiniz.mancala.domain.exception

import org.springframework.http.HttpStatus


class PlayerCannotPlayException(message: String?) : ApiException(HttpStatus.BAD_REQUEST, message)