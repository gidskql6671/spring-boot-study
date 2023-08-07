package dong.kotlin_study.dto

import dong.kotlin_study.dto.group.ValidationGroup1
import dong.kotlin_study.dto.group.ValidationGroup2
import jakarta.validation.constraints.*

data class ValidatedRequestDto(
    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    @field:Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    val phoneNumber: String,

    @field:Min(value = 20, groups = [ValidationGroup1::class])
    @field:Max(value = 40, groups = [ValidationGroup1::class])
    val age: Int,

    @field:Size(min = 0, max = 40)
    val description: String,

    @field:Positive(groups = [ValidationGroup2::class])
    val count: Int,

    @field:AssertTrue
    val booleanCheck: Boolean
)
