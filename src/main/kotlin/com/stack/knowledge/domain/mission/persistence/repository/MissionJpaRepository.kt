package com.stack.knowledge.domain.mission.persistence.repository

import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MissionJpaRepository : CrudRepository<MissionJpaEntity, UUID> {
    fun findAllByUserId(userId: UUID): List<MissionJpaEntity>
    fun findByMissionStatusOrderByCreatedAtDesc(missionStatus: MissionStatus): List<MissionJpaEntity>
}