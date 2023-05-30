package com.stack.knowledege.domain.teacher.application.spi

import com.stack.knowledege.domain.teacher.domain.Teacher

interface CommandTeacherPort {
    fun save(teacher: Teacher)
}