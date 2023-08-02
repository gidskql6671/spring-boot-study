package dong.kotlin_study.controller

import dong.kotlin_study.common.logger
import dong.kotlin_study.dto.ValidRequestDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/validation")
class ValidationController {
    private val log = logger()

    @PostMapping("/valid")
    fun checkValidationByValid(
        @Valid @RequestBody validRequestDto: ValidRequestDto
    ) : ResponseEntity<String> {
        log.info(validRequestDto.toString())

        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString())
    }
}