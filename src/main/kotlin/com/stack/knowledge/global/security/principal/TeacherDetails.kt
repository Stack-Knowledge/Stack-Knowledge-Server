package com.stack.knowledge.global.security.principal

import com.stack.knowledge.domain.user.domain.constant.Authority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class TeacherDetails(
    private val teacherId: UUID
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(Authority.ROLE_TEACHER.name))

    override fun getPassword(): String? = null

    override fun getUsername(): String = teacherId.toString()

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = false

}