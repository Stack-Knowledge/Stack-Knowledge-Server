package com.stack.knowledge.domain.point.persistence.repository

import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.point.persistence.entity.PointJpaEntity
import org.springframework.data.repository.CrudRepository

interface PointJpaRepository : CrudRepository<PointJpaEntity, Long> {
    fun findByMission(missionJpaEntity: MissionJpaEntity): PointJpaEntity
    fun findTopByOrderByMissionPointDesc(): PointJpaEntity?
}