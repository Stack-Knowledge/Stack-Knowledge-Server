package com.stack.knowledege.domain.user.application.validator

import com.stack.knowledege.domain.mission.exception.ForbiddenCommandMissionException
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.UserRole
import org.springframework.stereotype.Component

@Component
class UserValidatorImpl : UserValidator {
    override fun validateUserTeacher(user: User) {
        if (user.roles.firstOrNull() != UserRole.ROLE_TEACHER)
            throw ForbiddenCommandMissionException()
    }
}