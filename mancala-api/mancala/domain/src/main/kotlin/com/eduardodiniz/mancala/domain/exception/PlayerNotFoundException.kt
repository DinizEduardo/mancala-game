package com.eduardodiniz.mancala.domain.exception

import org.springframework.http.HttpStatus


class PlayerNotFoundException(message: String?) : ApiException(HttpStatus.NOT_FOUND, message)