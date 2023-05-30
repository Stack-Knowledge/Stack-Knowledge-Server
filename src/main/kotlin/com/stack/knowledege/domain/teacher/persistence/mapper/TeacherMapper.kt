package com.stack.knowledege.domain.teacher.persistence.mapper

import com.stack.knowledege.domain.teacher.domain.Teacher
import com.stack.knowledege.domain.teacher.persistence.entity.TeacherJpaEntity
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.persistence.mapper.UserMapper
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class TeacherMapper(
    private val queryUserPort: QueryUserPort,
    private val userMapper: UserMapper
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
        val user = userMapper.toEntity(queryUserPort.queryUserById(domain.id))
        return domain.let {
            TeacherJpaEntity(
                id = it.id,
                user = user,
                subject = it.subject
            )
        }
    }
}