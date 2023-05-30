package com.stack.knowledege.domain.teacher.persistence

import com.stack.knowledege.domain.teacher.application.spi.TeacherPort
import com.stack.knowledege.domain.teacher.domain.Teacher
import com.stack.knowledege.domain.teacher.persistence.mapper.TeacherMapper
import com.stack.knowledege.domain.teacher.persistence.repository.TeacherJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class TeacherPersistenceAdapter(
    private val teacherJpaRepository: TeacherJpaRepository,
    private val teacherMapper: TeacherMapper
) : TeacherPort{
    override fun save(teacher: Teacher) {
        teacherJpaRepository.save(teacherMapper.toEntity(teacher))
    }

    override fun queryTeacherById(id: UUID): Teacher? =
        teacherMapper.toDomain(teacherJpaRepository.findByIdOrNull(id))
}