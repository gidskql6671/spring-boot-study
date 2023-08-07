package dong.kotlin_study.common.security

import dong.kotlin_study.common.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

class CustomAccessDeniedHandler : AccessDeniedHandler {
    private val log = logger()

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        log.info("[handle] 접근이 막혔을 경우 경로 리다이렉션")
        response.sendRedirect("/sign-api/exception")
    }

}
