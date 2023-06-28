package com.stack.knowledege.domain.student.persistence

import com.stack.knowledege.domain.student.application.spi.StudentPort
import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.student.persistence.mapper.StudentMapper
import com.stack.knowledege.domain.student.persistence.repository.StudentJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class StudentPersistenceAdapter(
    private val studentJpaRepository: StudentJpaRepository,
    private val studentMapper: StudentMapper
) : StudentPort {

    override fun save(student: Student): Student =
        studentMapper.toDomain(studentJpaRepository.save(studentMapper.toEntity(student)))!!

    override fun queryStudentById(id: UUID): Student? =
        studentMapper.toDomain(studentJpaRepository.findByIdOrNull(id))

    override fun queryStudentsPointDesc(): List<Student> =
        studentJpaRepository.findAllByOrderByPointDesc().map { studentMapper.toDomain(it)!! }
}