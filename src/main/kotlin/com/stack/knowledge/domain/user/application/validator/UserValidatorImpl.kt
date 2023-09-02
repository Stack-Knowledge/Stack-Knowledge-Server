package com.stack.knowledge.domain.user.application.validator

import com.stack.knowledge.domain.mission.exception.ForbiddenCommandMissionException
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.Authority
import org.springframework.stereotype.Component

@Component
class UserValidatorImpl : UserValidator {
    override fun validateUserTeacher(user: User) {
        if (user.authority != Authority.ROLE_TEACHER)
            throw ForbiddenCommandMissionException()
    }
}