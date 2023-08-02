package dong.kotlin_study.exception

import org.springframework.http.HttpStatus

class CustomException(
    val httpStatus: HttpStatus,
    override val message: String = "",
    override val cause: Throwable? = null
) : RuntimeException(message, cause) {

    val httpStatusCode = httpStatus.value()
    val httpStatusType = httpStatus.reasonPhrase
}