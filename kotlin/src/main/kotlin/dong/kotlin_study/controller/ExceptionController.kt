package dong.kotlin_study.controller

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
}