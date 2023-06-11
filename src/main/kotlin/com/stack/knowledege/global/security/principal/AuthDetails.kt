package com.stack.knowledege.global.security.principal

import com.stack.knowledege.domain.user.persistence.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val user: UserEntity
):  UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = user.roles

    override fun getPassword(): String? = null

    override fun getUsername(): String = user.id.toString()

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = false

}