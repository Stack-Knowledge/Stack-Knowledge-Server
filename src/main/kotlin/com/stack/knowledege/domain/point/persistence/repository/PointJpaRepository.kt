package com.stack.knowledege.domain.point.persistence.repository

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.point.persistence.entity.PointJpaEntity
import org.springframework.data.repository.CrudRepository

interface PointJpaRepository : CrudRepository<PointJpaEntity, Long> {
    fun findByMission(mission: Mission): PointJpaEntity
    fun findTopByOrderByIdDesc(): PointJpaEntity
}