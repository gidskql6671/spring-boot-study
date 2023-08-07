package dong.kotlin_study.common.security

import dong.kotlin_study.common.logger
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    private val userDetailsService: UserDetailsService,
) {

    private val log = logger()
    private val tokenValidMillisecond = 1000L * 60 * 60

    @Value("\${property.jwt.secret}")
    private var secretKey: String = "secretKey"

    @PostConstruct
    protected fun init() {
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 시작")
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 완료")
    }

    fun createToken(userUid: String, roles: List<String>): String {
        log.info("[createToken] 토큰 생성 시작")

        val claims = Jwts.claims().setSubject(userUid).also {
            it["roles"] = roles
        }

        val now = Date()

        val token = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenValidMillisecond))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()

        log.info("[createToken] 토큰 생성 완료")
        return token
    }

    fun getAuthentication(token: String): Authentication {
        log.info("[getAuthentication] 토큰 인증 정보 조회 시작")
        val userDetails = userDetailsService.loadUserByUsername(this.getUsername(token))
        log.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails Username : {}", userDetails.username)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출")
        val info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info)

        return info
    }

    fun resolveToken(request: HttpServletRequest): String? = request.getHeader("X-AUTH-TOKEN")

    fun validateToken(token: String): Boolean {
        log.info("[validateToken] 토큰 유효 체크 시작")

        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)

            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            log.info("[validateToken] 토큰 유효 체크 예외 발생")

            false
        }
    }
}