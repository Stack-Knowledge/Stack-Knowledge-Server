package com.stack.knowledge.domain.time.persistence.repository

import com.stack.knowledge.domain.time.persistence.entity.TimeJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TimeJpaRepository : CrudRepository<TimeJpaEntity, UUID>