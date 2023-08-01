package com.stack.knowledege.domain.student.persistence.mapper

import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.persistence.repository.UserRepository
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class StudentMapper(
    private val userRepository: UserRepository
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
        val user = userRepository.findByIdOrNull(domain.user) ?: throw UserNotFoundException()

        return StudentJpaEntity(
            id = domain.id,
            currentPoint = domain.currentPoint,
            cumulatePoint = domain.cumulatePoint,
            user = user
        )
    }
}