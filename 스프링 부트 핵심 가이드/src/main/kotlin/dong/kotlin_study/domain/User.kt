package dong.kotlin_study.domain


import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

@Entity
class User(
    @Column(nullable = false, unique = true)
    val uid: String,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private var password: String,

    @Column(nullable = false)
    var name: String,

    @ElementCollection(fetch = FetchType.EAGER)
    val roles: MutableList<String> = ArrayList()
): UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.roles.stream().map{ SimpleGrantedAuthority(it) }.collect(Collectors.toList())
    }

    override fun getPassword(): String = this.password
    override fun getUsername(): String = this.uid
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}