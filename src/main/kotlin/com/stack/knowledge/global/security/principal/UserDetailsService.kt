package com.stack.knowledge.global.security.principal

import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.persistence.repository.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class UserDetailsService(
    private val userJpaRepository: UserJpaRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userJpaRepository.findByIdOrNull(UUID.fromString(username))
            .let { it ?: throw UserNotFoundException() }
            .let { UserDetails(it) }
}