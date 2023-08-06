package dong.kotlin_study.common.security

import dong.kotlin_study.common.logger
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider): OncePerRequestFilter() {
    private val log = logger()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = jwtTokenProvider.resolveToken(request)
        log.info("[doFilterInternal] token 값 추출 완료. token: {}", token)

        log.info("[doFilterInternal] token 값 유효성 체크 시작")
        if (token != null && jwtTokenProvider.validateToken(token)) {
            val authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
            log.info("[doFilterInternal] token 값 유효성 체크 완료")
        }

        filterChain.doFilter(request, response)
    }
}