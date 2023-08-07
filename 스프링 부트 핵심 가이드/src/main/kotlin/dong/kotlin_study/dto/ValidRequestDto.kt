package dong.kotlin_study.dto

import dong.kotlin_study.common.annotation.Telephone
import jakarta.validation.constraints.*

data class ValidRequestDto(
    @field:NotBlank val name: String,
    @field:Email val email: String,
    @field:Telephone val phoneNumber: String,
    @field:Min(value = 20) @field:Max(value = 40) val age: Int,
    @field:Size(min = 0, max = 40) val description: String,
    @field:Positive val count: Int,
    @field:AssertTrue val booleanCheck: Boolean
)
