package dong.kotlin_study.dto

data class SignInResultDto(
    val success: Boolean,
    val code: Int,
    val msg: String,
    val token: String
)
