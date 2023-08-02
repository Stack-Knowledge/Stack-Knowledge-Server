package com.stack.knowledege.domain.mission.persistence.repository

import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface MissionJpaRepository : CrudRepository<MissionJpaEntity, UUID> {
    fun findByUser(userJpaEntity: UserJpaEntity): MissionJpaEntity
    fun findByMissionStatus(missionStatus: MissionStatus): List<MissionJpaEntity>
}