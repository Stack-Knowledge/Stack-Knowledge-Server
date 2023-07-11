package com.stack.knowledege.domain.mission.persistence.repository

import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface MissionRepository : CrudRepository<MissionJpaEntity, UUID> {
    fun findByUser(userJpaEntity: UserJpaEntity): MissionJpaEntity
}