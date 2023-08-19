package com.stack.knowledege.domain.student.persistence.mapper

import com.stack.knowledege.domain.point.exception.PointNotFoundException
import com.stack.knowledege.domain.point.persistence.repository.PointJpaRepository
import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.persistence.repository.UserJpaRepository
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class StudentMapper(
    private val userJpaRepository: UserJpaRepository,
    private val pointJpaRepository: PointJpaRepository
) : GenericMapper<Student, StudentJpaEntity> {
    override fun toDomain(entity: StudentJpaEntity?): Student? =
        entity?.let {
            Student(
                id = it.id,
                currentPoint = it.currentPoint,
                cumulatePoint = it.cumulatePoint,
                point = it.point.id,
                user = it.user.id
            )
        }

    override fun toEntity(domain: Student): StudentJpaEntity {
        val user = userJpaRepository.findByIdOrNull(domain.user) ?: throw UserNotFoundException()
        val point = pointJpaRepository.findByIdOrNull(domain.point) ?: throw PointNotFoundException()

        return StudentJpaEntity(
            id = domain.id,
            currentPoint = domain.currentPoint,
            cumulatePoint = domain.cumulatePoint,
            point = point,
            user = user
        )
    }
}