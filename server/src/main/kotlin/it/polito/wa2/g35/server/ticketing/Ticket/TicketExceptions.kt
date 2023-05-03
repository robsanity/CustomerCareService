package it.polito.wa2.g35.server.ticketing.Ticket

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class TicketExceptions: ResponseEntityExceptionHandler() {
    @ExceptionHandler(TicketNotFoundException::class)
    fun handleProductNotFound(e: TicketNotFoundException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.NOT_FOUND, e.message!!)
    @ExceptionHandler(InvalidTicketStatusValueException::class)
    fun handleTicketStatusValueNotFound(e: InvalidTicketStatusValueException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.NOT_FOUND, e.message!!)
}

class TicketNotFoundException(message : String) : RuntimeException(message)
class InvalidTicketStatusValueException(message : String) : RuntimeException(message)

