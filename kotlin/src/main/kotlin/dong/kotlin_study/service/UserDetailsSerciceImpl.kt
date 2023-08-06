package dong.kotlin_study.service

import dong.kotlin_study.common.logger
import dong.kotlin_study.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsSerciceImpl(
    private val userRepository: UserRepository
): UserDetailsService {

    private val log = logger()

    override fun loadUserByUsername(username: String): UserDetails? {
        log.info("[loadUserByUsername] loadUserByUsername 수행. username : {}", username)
        return userRepository.getByUid(username)
    }
}