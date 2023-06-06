package com.stack.knowledege.domain.solvation.persistence.repository

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.solvation.persistence.entity.SolvationJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SolvationRepository : CrudRepository<SolvationJpaEntity, UUID> {
    fun queryByMission(mission: Mission): SolvationJpaEntity?
}