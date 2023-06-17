package com.stack.knowledege.domain.teacher.application.usecase

import com.stack.knowledege.domain.teacher.application.spi.CommandTeacherPort
import com.stack.knowledege.domain.teacher.domain.Teacher
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class CreateTeacherUseCase(
    private val commandTeacherPort: CommandTeacherPort
) {
    fun execute(user: User, subject: String) {
        commandTeacherPort.save(Teacher(
            id = UUID.randomUUID(),
            subject = subject,
            user = user.id
        ))
    }
}