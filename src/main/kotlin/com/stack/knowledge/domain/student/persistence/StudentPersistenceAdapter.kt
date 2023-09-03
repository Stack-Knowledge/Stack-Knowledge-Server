package com.stack.knowledge.domain.student.persistence

import com.stack.knowledge.domain.student.application.spi.StudentPort
import com.stack.knowledge.domain.student.domain.Student
import com.stack.knowledge.domain.student.persistence.mapper.StudentMapper
import com.stack.knowledge.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.persistence.mapper.UserMapper
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