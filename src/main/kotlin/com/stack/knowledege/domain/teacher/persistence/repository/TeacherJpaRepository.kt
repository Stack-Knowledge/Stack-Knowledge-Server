package com.stack.knowledege.domain.teacher.persistence.repository

import com.stack.knowledege.domain.teacher.persistence.entity.TeacherJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TeacherJpaRepository : CrudRepository<TeacherJpaEntity, UUID>