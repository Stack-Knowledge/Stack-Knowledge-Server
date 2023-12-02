package com.stack.knowledge.domain.mission.persistence.repository

import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MissionJpaRepository : CrudRepository<MissionJpaEntity, UUID> {
    fun findAllByMissionStatus(missionStatus: MissionStatus): List<MissionJpaEntity>
    fun findAllByUserIdOrderByCreatedAtDesc(userId: UUID): List<MissionJpaEntity>
    @EntityGraph(attributePaths = ["user"])
    fun findAllByMissionStatusOrderByCreatedAtDesc(missionStatus: MissionStatus): List<MissionJpaEntity>
}