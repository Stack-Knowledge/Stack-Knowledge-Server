package com.stack.knowledge.domain.user.application.validator

import com.stack.knowledge.domain.user.domain.User

interface UserValidator {
    fun validateUserTeacher(user: User)
}