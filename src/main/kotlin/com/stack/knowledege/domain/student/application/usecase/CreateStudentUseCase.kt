package com.stack.knowledege.domain.student.application.usecase

import com.stack.knowledege.domain.student.application.spi.CommandStudentPort
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class CreateStudentUseCase(
    private val commandStudentPort: CommandStudentPort,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(user: User): UUID {
        val student = Student(
            id = user.id,
            currentPoint = 0,
            cumulatePoint = 0,
            user = user.id
        )
        println(user.id)
        print("studentId ================== ")
        println(student.id)
        commandStudentPort.save(student)
        queryStudentPort.queryStudentByUser(user).let {
            print("진짜 userId ================== ")
            println(it!!.id)
        }

        return student.id
    }
}