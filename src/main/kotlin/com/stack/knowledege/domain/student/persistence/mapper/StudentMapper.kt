package com.stack.knowledege.domain.student.persistence.mapper

import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.persistence.mapper.UserMapper
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class StudentMapper(
    private val queryUserPort: QueryUserPort,
    private val userMapper: UserMapper
) : GenericMapper<Student, StudentJpaEntity> {
    override fun toDomain(entity: StudentJpaEntity?): Student? =
        entity?.let {
            Student(
                id = it.id,
                email = it.user.email,
                name = it.user.name,
                roles = it.user.roles,
                grade = it.grade,
                classes = it.classes,
                number = it.number,
                point = it.point
            )
        }


    override fun toEntity(domain: Student): StudentJpaEntity {
        val user = userMapper.toEntity(queryUserPort.queryCurrentUser())
        return domain.let {
            StudentJpaEntity(
                id = it.id,
                user = user,
                grade = it.grade,
                classes = it.classes,
                number = it.number,
                point = it.point
            )
        }
    }
}