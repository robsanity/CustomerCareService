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
    @ExceptionHandler(TicketConflictException::class)
    fun handleTicketConflict(e: TicketConflictException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.CONFLICT, e.message!!)
    @ExceptionHandler(TicketStatusValueInvalidException::class)
    fun handleTicketStatusValueInvalid(e: TicketStatusValueInvalidException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.CONFLICT, e.message!!)
    @ExceptionHandler(TicketPriorityInvalidException::class)
    fun handleTicketPriorityInvalid(e: TicketPriorityInvalidException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.CONFLICT, e.message!!)
}

class TicketNotFoundException(message : String) : RuntimeException(message)
class TicketConflictException(message: String) : RuntimeException(message)
class TicketStatusValueInvalidException(message : String) : RuntimeException(message)
class TicketPriorityInvalidException(message: String) : RuntimeException(message)