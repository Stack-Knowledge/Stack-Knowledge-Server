package com.stack.knowledege.domain.user.persistence

import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.persistence.mapper.UserMapper
import com.stack.knowledege.domain.user.persistence.repository.UserJpaRepository
import com.stack.knowledege.global.error.exception.InvalidRoleException
import com.stack.knowledege.global.security.spi.SecurityPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper,
    private val securityPort: SecurityPort
) : UserPort {
    override fun save(user: User): User =
        userMapper.toDomain(userJpaRepository.save(userMapper.toEntity(user)))!!

    override fun queryUserById(id: UUID): User? =
        userMapper.toDomain(userJpaRepository.findByIdOrNull(id))

    override fun queryUserRoleByEmail(email: String, authority: String): Authority {
        val user = userJpaRepository.findByEmail(email) ?: return when (authority) {
            "ROLE_STUDENT" -> Authority.ROLE_STUDENT
            "ROLE_TEACHER" -> Authority.ROLE_TEACHER
            else -> { throw InvalidRoleException() }
        }
        return user.authority
    }

    override fun queryUserByEmail(email: String): User? =
        userMapper.toDomain(userJpaRepository.findByEmail(email))

    override fun queryExistByEmail(email: String): Boolean =
        userJpaRepository.existsByEmail(email)

    override fun queryCurrentUser(): User =
        userMapper.toDomain(userJpaRepository.findByIdOrNull(securityPort.queryCurrentUserId()))
            .let { it ?: throw UserNotFoundException() }

    override fun queryAllUser(): List<User> =
        userJpaRepository.findAll().map { userMapper.toDomain(it)!! }
}