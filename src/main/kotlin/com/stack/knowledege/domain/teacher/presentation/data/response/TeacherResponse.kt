package com.stack.knowledege.domain.teacher.presentation.data.response

import com.stack.knowledege.domain.user.domain.constant.UserRole
import java.util.UUID

data class TeacherResponse(
    val id: UUID,
    val email: String,
    val name: String,
    val roles: MutableList<UserRole> = mutableListOf(),
    val subject: String
)