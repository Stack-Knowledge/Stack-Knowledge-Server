package com.stack.knowledege.domain.teacher.persistence.mapper

import com.stack.knowledege.domain.teacher.domain.Teacher
import com.stack.knowledege.domain.teacher.persistence.entity.TeacherJpaEntity
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.persistence.repository.UserRepository
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class TeacherMapper(
    private val userRepository: UserRepository
) : GenericMapper<Teacher, TeacherJpaEntity> {
    override fun toDomain(entity: TeacherJpaEntity?): Teacher? =
        entity?.let {
            Teacher(
                id = it.id,
                email = it.user.email,
                name = it.user.name,
                roles = it.user.roles,
                subject = it.subject
            )
        }


    override fun toEntity(domain: Teacher): TeacherJpaEntity {
        val user = userRepository.findByEmail(domain.email)
            ?: throw UserNotFoundException()
        return domain.let {
            TeacherJpaEntity(
                id = it.id,
                user = user,
                subject = it.subject
            )
        }
    }
}