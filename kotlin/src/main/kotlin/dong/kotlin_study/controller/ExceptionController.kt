package dong.kotlin_study.controller

import dong.kotlin_study.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/exception")
class ExceptionController {

    @GetMapping
    fun getRuntimeException() {
        throw RuntimeException("getRuntimeException 메서드 호출")
    }

    @GetMapping("/custom")
    fun getCustomException() {
        throw CustomException(HttpStatus.BAD_REQUEST, "getCustomException 메서드 호출")
    }
}