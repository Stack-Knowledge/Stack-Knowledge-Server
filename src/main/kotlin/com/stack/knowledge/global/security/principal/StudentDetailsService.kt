package com.stack.knowledge.global.security.principal

import com.stack.knowledge.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class StudentDetailsService(
    private val studentJpaRepository: StudentJpaRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        studentJpaRepository.findByIdOrNull(UUID.fromString(username))
            .let { it ?: throw UserNotFoundException() }
            .let { StudentDetails(it.id) }
}