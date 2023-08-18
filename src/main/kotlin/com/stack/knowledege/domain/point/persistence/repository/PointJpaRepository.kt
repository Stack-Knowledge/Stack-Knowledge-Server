package com.stack.knowledege.domain.point.persistence.repository

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.point.persistence.entity.PointJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PointJpaRepository : CrudRepository<PointJpaEntity, UUID> {
    fun findByMission(mission: Mission): PointJpaEntity
    fun findTopByIdDesc(): PointJpaEntity
}