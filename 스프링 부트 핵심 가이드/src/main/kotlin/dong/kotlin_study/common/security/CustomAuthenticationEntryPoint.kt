package dong.kotlin_study.common.security

import com.fasterxml.jackson.databind.ObjectMapper
import dong.kotlin_study.common.logger
import dong.kotlin_study.dto.EntryPointErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val log = logger()

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.info("[commence] 인증 실패로 response.sendError 발생")

        val objectMapper = ObjectMapper()
        val entryPointErrorResponse = EntryPointErrorResponse("인증이 실패하였습니다.")

        response.status = 401
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        response.writer.write(objectMapper.writeValueAsString(entryPointErrorResponse))
    }

}
