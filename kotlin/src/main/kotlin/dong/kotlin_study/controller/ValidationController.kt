package dong.kotlin_study.controller

import dong.kotlin_study.common.logger
import dong.kotlin_study.dto.ValidRequestDto
import dong.kotlin_study.dto.ValidatedRequestDto
import dong.kotlin_study.dto.group.ValidationGroup1
import dong.kotlin_study.dto.group.ValidationGroup2
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
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

    @PostMapping("/validated")
    fun checkValidation(
        @Validated @RequestBody validatedRequestDto: ValidatedRequestDto
    ) : ResponseEntity<String> {
        log.info(validatedRequestDto.toString())

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString())
    }

    @PostMapping("/validated/group1")
    fun checkValidation1(
        @Validated(ValidationGroup1::class) @RequestBody validatedRequestDto: ValidatedRequestDto
    ) : ResponseEntity<String> {
        log.info(validatedRequestDto.toString())

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString())
    }

    @PostMapping("/validated/group2")
    fun checkValidation2(
        @Validated(ValidationGroup2::class) @RequestBody validatedRequestDto: ValidatedRequestDto
    ) : ResponseEntity<String> {
        log.info(validatedRequestDto.toString())

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString())
    }

    @PostMapping("/validated/all-group")
    fun checkValidation3(
        @Validated(value = [ValidationGroup1::class, ValidationGroup2::class])
        @RequestBody
        validatedRequestDto: ValidatedRequestDto
    ) : ResponseEntity<String> {
        log.info(validatedRequestDto.toString())

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString())
    }
}