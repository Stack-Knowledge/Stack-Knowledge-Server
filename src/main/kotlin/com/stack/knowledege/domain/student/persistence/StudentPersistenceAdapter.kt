package com.stack.knowledege.domain.student.persistence

import com.stack.knowledege.domain.student.application.spi.StudentPort
import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.student.persistence.mapper.StudentMapper
import com.stack.knowledege.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.persistence.mapper.UserMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class StudentPersistenceAdapter(
    private val studentJpaRepository: StudentJpaRepository,
    private val studentMapper: StudentMapper,
    private val userMapper: UserMapper
) : StudentPort {

    override fun save(student: Student) {
        studentJpaRepository.save(studentMapper.toEntity(student))
    }

    override fun queryStudentById(id: UUID): Student? =
        studentMapper.toDomain(studentJpaRepository.findByIdOrNull(id))

    override fun queryStudentsPointDesc(): List<Student> =
        studentJpaRepository.findAllByOrderByCumulatePointDesc().map { studentMapper.toDomain(it)!! }

    override fun queryStudentByUser(user: User): Student? =
        studentMapper.toDomain(studentJpaRepository.findByUser(userMapper.toEntity(user)))

    override fun existStudentByUser(user: User): Boolean =
        studentJpaRepository.existsByUser(userMapper.toEntity(user))
}