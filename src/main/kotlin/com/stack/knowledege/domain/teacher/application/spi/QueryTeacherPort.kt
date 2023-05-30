package com.stack.knowledege.domain.teacher.application.spi

import com.stack.knowledege.domain.teacher.domain.Teacher
import java.util.UUID

interface QueryTeacherPort {
    fun queryTeacherById(id: UUID): Teacher?
}