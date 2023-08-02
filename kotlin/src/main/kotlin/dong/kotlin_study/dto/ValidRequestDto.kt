package dong.kotlin_study.dto

import jakarta.validation.constraints.*

data class ValidRequestDto(
    @field:NotBlank val name: String,
    @field:Email val email: String,
    @field:Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$") val phoneNumber: String,
    @field:Min(value = 20) @field:Max(value = 40) val age: Int,
    @field:Size(min = 0, max = 40) val description: String,
    @field:Positive val count: Int,
    @field:AssertTrue val booleanCheck: Boolean
)
