package com.stack.knowledge.domain.student.persistence.mapper

import com.stack.knowledge.domain.student.domain.Student
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.persistence.repository.UserJpaRepository
import com.stack.knowledge.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class StudentMapper(
    private val userJpaRepository: UserJpaRepository
) : GenericMapper<Student, StudentJpaEntity> {
    override fun toDomain(entity: StudentJpaEntity?): Student? =
        entity?.let {
            Student(
                id = it.id,
                currentPoint = it.currentPoint,
                cumulatePoint = it.cumulatePoint,
                user = it.user.id
            )
        }

    override fun toEntity(domain: Student): StudentJpaEntity {
        val user = userJpaRepository.findByIdOrNull(domain.user) ?: throw UserNotFoundException()

        return StudentJpaEntity(
            id = domain.id,
            currentPoint = domain.currentPoint,
            cumulatePoint = domain.cumulatePoint,
            user = user
        )
    }
}