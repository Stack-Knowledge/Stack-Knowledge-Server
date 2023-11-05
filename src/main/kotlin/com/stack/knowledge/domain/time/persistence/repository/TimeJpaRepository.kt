package com.stack.knowledge.domain.time.persistence.repository

import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.time.persistence.entity.TimeJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TimeJpaRepository : CrudRepository<TimeJpaEntity, UUID> {
    fun findByMissionJpaEntity(missionJpaEntity: MissionJpaEntity): TimeJpaEntity?
}