package com.stack.knowledege.domain.student.persistence.mapper

import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.student.persistence.entity.StudentJpaEntity
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Component
interface StudentMapper {
    fun toDomain(entity: StudentJpaEntity?): Student?
    fun toEntity(domain: Student): StudentJpaEntity
}