package com.eduardodiniz.mancala.domain.exception.handler

import com.eduardodiniz.mancala.domain.exception.ApiException
import org.apache.logging.log4j.util.Strings
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(ex: ApiException): ResponseEntity<ExceptionMessage> {
        val httpStatus = ex.httpStatus
        val message = ex.message ?: Strings.EMPTY
        return ResponseEntity(ExceptionMessage(httpStatus, message), httpStatus);
    }

}
