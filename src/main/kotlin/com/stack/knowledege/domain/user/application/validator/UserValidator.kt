package com.stack.knowledege.domain.user.application.validator

import com.stack.knowledege.domain.user.domain.User


interface UserValidator {
    fun validateUserTeacher(user: User)
}