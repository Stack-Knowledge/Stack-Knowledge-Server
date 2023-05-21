package com.stack.knowledege.domain.user.persistence

import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.UserRole
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.persistence.mapper.UserMapper
import com.stack.knowledege.domain.user.persistence.repository.UserRepository
import com.stack.knowledege.global.error.exception.InvalidRoleException
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

    override fun queryUserById(id: UUID): User? {
            val user = userRepository.findByIdOrNull(id)
            return userMapper.toDomain(user)
    }

    override fun queryUserRoleByEmail(email: String, role: String): UserRole {
        val user = userRepository.findByEmail(email) ?: return when (role) {
            "ROLE_STUDENT" -> UserRole.ROLE_STUDENT
            "ROLE_TEACHER" -> UserRole.ROLE_TEACHER
            "ROLE_ADMIN" -> UserRole.ROLE_ADMIN
            else -> { throw InvalidRoleException() }
        }
        return user.roles.firstOrNull() ?: throw UserNotFoundException()
    }

    override fun queryUserByEmail(email: String): User? {
        val user = userRepository.findByEmail(email)
        return userMapper.toDomain(user)
    }

    override fun queryExistByEmail(email: String): Boolean =
        userRepository.existsByEmail(email)


    override fun queryCurrentUser(): User {
        return userMapper.toDomain(userRepository.findByIdOrNull(securityPort.queryCurrentUserId()))
            .let {
                it ?: throw UserNotFoundException()
            }
    }

    override fun queryUserPointByEmail(email: String): Int =
        userRepository.findUserPointByEmail(email)
}