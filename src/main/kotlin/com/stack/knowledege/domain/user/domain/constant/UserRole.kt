package com.stack.knowledege.domain.user.domain.constant

import org.springframework.security.core.GrantedAuthority

enum class UserRole : GrantedAuthority {

    ROLE_STUDENT, ROLE_TEACHER, ROLE_ADMIN;

    override fun getAuthority(): String = name
}