package com.eduardodiniz.mancala.domain.exception.handler

import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class ExceptionMessage(val status: HttpStatus, val message: String) {

    val timestamp: String = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

}