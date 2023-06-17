package com.stack.knowledege.domain.teacher.domain

import com.stack.knowledege.global.annotation.Aggregate
import java.util.*

@Aggregate
class Teacher(
    val id: UUID,
    val subject: String,
    val user: UUID
)