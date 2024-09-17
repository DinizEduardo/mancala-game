package com.eduardodiniz.mancala.domain.exception

import org.springframework.http.HttpStatus


class PitNotFoundException(message: String?) : ApiException(HttpStatus.NOT_FOUND, message)