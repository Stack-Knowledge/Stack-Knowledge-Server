package com.stack.knowledge.common.service.impl

import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.common.spi.SecurityPort
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.user.application.spi.QueryUserPort
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.global.security.exception.InvalidRoleException
import org.springframework.stereotype.Service
import java.util.*

@Service
class SecurityServiceImpl(
    private val securityPort: SecurityPort,
    private val queryStudentPort: QueryStudentPort,
    private val queryUserPort: QueryUserPort
) : SecurityService {
    override fun queryCurrentUser(): User =
        when (securityPort.queryCurrentUserAuthority()) {
            Authority.ROLE_STUDENT.name -> {
                val student = queryStudentPort.queryStudentById(securityPort.queryCurrentUserId()) ?: throw StudentNotFoundException()
                queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()
            }
            Authority.ROLE_TEACHER.name -> {
                queryUserPort.queryUserById(securityPort.queryCurrentUserId()) ?: throw UserNotFoundException()
            }
            else -> throw InvalidRoleException()
        }

    override fun queryCurrentUserAuthority(): String =
        securityPort.queryCurrentUserAuthority()

    override fun queryCurrentUserId(): UUID =
        securityPort.queryCurrentUserId()
}