package dong.kotlin_study.exception

import dong.kotlin_study.common.logger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice(basePackages = ["dong.kotlin_study.controller"])
class CustomExceptionHandler {
    private val log = logger()

    @ExceptionHandler(value = [RuntimeException::class])
    fun handleException(e: RuntimeException, request: HttpServletRequest): ResponseEntity<Map<String, String>> {
        val responseHeader = HttpHeaders()
        val httpStatus = HttpStatus.BAD_REQUEST

        log.error("Advice 내 handleException 호출, {}, {}", request.requestURI, e.message)

        val map = HashMap<String, String>()
        map["error type"] = httpStatus.reasonPhrase
        map["code"] = "400"
        map["message"] = e.message.toString()

        return ResponseEntity(map, responseHeader, httpStatus)
    }
}