package it.polito.wa2.g35.server.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ProblemDetailsHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler(ProfileNotFoundException::class)
    fun handleProfileNotFound(e: ProfileNotFoundException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.NOT_FOUND, e.message!!)
    @ExceptionHandler(ProductNotFoundException::class)
    fun handleProductNotFound(e: ProductNotFoundException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.NOT_FOUND, e.message!!)
    @ExceptionHandler(DuplicateProfileException::class)
    fun handleDuplicateProduct(e: DuplicateProfileException) = ProblemDetail
        .forStatusAndDetail(HttpStatus.CONFLICT, e.message!!)
    @ExceptionHandler(PathVariableMissingException::class)
    fun handleProfileNotFound(e: PathVariableMissingException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.BAD_REQUEST, e.message!!)
}

class DuplicateProfileException(message : String) : RuntimeException(message)
class ProfileNotFoundException(message : String) : RuntimeException(message)
class ProductNotFoundException(message : String) : RuntimeException(message)
class PathVariableMissingException(message : String) : RuntimeException(message)
