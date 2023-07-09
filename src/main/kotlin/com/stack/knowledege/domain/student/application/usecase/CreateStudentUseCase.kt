package com.stack.knowledege.domain.student.application.usecase

import com.stack.knowledege.domain.student.application.spi.CommandStudentPort
import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class CreateStudentUseCase(
    private val commandStudentPort: CommandStudentPort
) {
    fun execute(user: User) {
        val student = Student(
            id = UUID.randomUUID(),
            point = 0,
            user = user.id
        )
        commandStudentPort.save(student)
    }
}