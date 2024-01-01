package com.stack.knowledge.domain.student.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.domain.student.application.spi.CommandStudentPort
import com.stack.knowledge.domain.student.domain.Student
import com.stack.knowledge.domain.user.domain.User
import java.util.*

@ServiceWithTransaction
class CreateStudentService(
    private val commandStudentPort: CommandStudentPort
) {
    fun execute(user: User): Student {
        val student = Student(
            id = UUID.randomUUID(),
            currentPoint = 0,
            cumulatePoint = 0,
            user = user.id
        )

        return commandStudentPort.save(student)
    }
}