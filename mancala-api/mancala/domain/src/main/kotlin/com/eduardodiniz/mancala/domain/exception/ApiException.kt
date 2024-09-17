package com.eduardodiniz.mancala.domain.exception

import org.springframework.http.HttpStatus


open class ApiException(val httpStatus: HttpStatus, message: String?) : RuntimeException(message)