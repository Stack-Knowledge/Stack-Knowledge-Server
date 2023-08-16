package com.stack.knowledege.common.service.impl

import com.stack.knowledege.common.service.SecurityService
import com.stack.knowledege.common.spi.SecurityPort
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.security.exception.InvalidRoleException
import org.springframework.stereotype.Service

@Service
class SecurityServiceImpl(
    private val securityPort: SecurityPort,
    private val queryStudentPort: QueryStudentPort,
    private val queryUserPort: QueryUserPort
) : SecurityService {
    override fun queryCurrentUser(): User =
        when (securityPort.queryCurrentUserAuthority()) {
            Authority.ROLE_STUDENT.name -> {
                val student = queryStudentPort.queryStudentById(securityPort.queryCurrentUserId()) ?: throw UserNotFoundException()
                queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()
            }
            Authority.ROLE_TEACHER.name -> {
                queryUserPort.queryUserById(securityPort.queryCurrentUserId()) ?: throw UserNotFoundException()
            }
            else -> throw InvalidRoleException()
        }
}