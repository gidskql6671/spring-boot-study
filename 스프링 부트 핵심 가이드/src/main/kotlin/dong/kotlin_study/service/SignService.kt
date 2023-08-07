package dong.kotlin_study.service

import dong.kotlin_study.common.logger
import dong.kotlin_study.common.security.JwtTokenProvider
import dong.kotlin_study.domain.User
import dong.kotlin_study.dto.SignInResultDto
import dong.kotlin_study.dto.SignUpResultDto
import dong.kotlin_study.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {

    private val log = logger()

    fun signUp(id: String, password: String, name: String, role: String): SignUpResultDto {
        log.info("[signUp] 회원 가입 정보 전달")

        val roles =
            if (role.equals("admin", ignoreCase = true)) {
                Collections.singletonList("ROLE_ADMIN")
            }
            else {
                Collections.singletonList("ROLE_USER")
            }

        val user = User(
            uid = id,
            name = name,
            password = passwordEncoder.encode(password),
            roles = roles
        )

        val savedUser = userRepository.save(user)

        log.info("[signUp] userEntity 값이 들어왔는지 확인 후 결과값 생성")

        return if (savedUser.name.isNotEmpty()) {
            log.info("[signUp] 정상 처리 완료")
            SignUpResultDto(success = true, code = 0, msg = "Success")
        }
        else {
            log.info("[signUp] 실패 처리 완료")
            SignUpResultDto(success = false, code = 1, msg = "Fail")
        }
    }

    fun signIn(id: String, password: String): SignInResultDto {
        log.info("[signIn] signDataHandler 로 회원 정보 요청")

        val user = userRepository.getByUid(id)!!
        log.info("[signIn] Id : {}", id)

        if (!passwordEncoder.matches(password, user.password)) {
            throw RuntimeException()
        }

        return SignInResultDto(
            success = true,
            code = 0,
            msg = "success",
            token = jwtTokenProvider.createToken(user.uid, user.roles)
        )
    }
}