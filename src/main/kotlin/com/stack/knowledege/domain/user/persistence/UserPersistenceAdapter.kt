package com.stack.knowledege.domain.user.persistence

import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.persistence.mapper.UserMapper
import com.stack.knowledege.domain.user.persistence.repository.UserRepository
import com.stack.knowledege.global.security.spi.SecurityPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val securityPort: SecurityPort
) : UserPort {
    override fun saveUser(user: User): User? =
        userMapper.toDomain(userRepository.save(userMapper.toEntity(user)))

    override fun queryCurrentUser(): User =
        userMapper.toDomain(userRepository.findByIdOrNull(securityPort.queryCurrentUserId()))
            .let { it ?: throw UserNotFoundException() }

    override fun queryUserById(id: UUID): User? =
        userMapper.toDomain(userRepository.findByIdOrNull(id) ?: throw UserNotFoundException())
}