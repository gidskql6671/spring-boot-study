package dong.kotlin_study.controller

import dong.kotlin_study.common.logger
import dong.kotlin_study.dto.SignInResultDto
import dong.kotlin_study.dto.SignUpResultDto
import dong.kotlin_study.service.SignService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sign-api")
class SignController(private val signService: SignService) {
    private val log = logger()

    @PostMapping("/sign-in")
    fun signIn(@RequestParam id: String, @RequestParam password: String): SignInResultDto {
        log.info("[signIn], 로그인을 시도하고 있습니다. id : {}", id)
        val signInResultDto = signService.signIn(id, password)

        if (signInResultDto.code == 0) {
            log.info("[signIn] 정상적으로 로그인되었습니다. id: {}, token: {}", id, signInResultDto.token)
        }

        return signInResultDto
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestParam id: String,
        @RequestParam password: String,
        @RequestParam name: String,
        @RequestParam role: String,
    ): SignUpResultDto {
        log.info("[signIn], 회원가입을 시도하고 있습니다. id : {}", id)
        val signUpResultDto = signService.signUp(id, password, name, role)

        log.info("[signUp] 회원가입을 완료했습니다. id : {}", id)
        return signUpResultDto
    }

    @GetMapping("/exception")
    fun exceptionTest() { throw RuntimeException("접근이 금지되었습니다.") }

    @ExceptionHandler(RuntimeException::class)
    fun exceptionHandler(e: RuntimeException): ResponseEntity<Map<String, String>> {
        val httpStatus = HttpStatus.BAD_REQUEST

        log.error("exceptionHandler 호출, {}, {}", e.cause, e.message)

        val map = HashMap<String, String>().also {
            it["error type"] = httpStatus.reasonPhrase
            it["code"] = "400"
            it["message"] = "에러 발생"
        }

        return ResponseEntity(map, HttpHeaders(), httpStatus)
    }
}