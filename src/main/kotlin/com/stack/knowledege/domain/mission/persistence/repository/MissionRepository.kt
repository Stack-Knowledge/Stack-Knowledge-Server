package com.stack.knowledege.domain.mission.persistence.repository

import com.stack.knowledege.domain.mission.persistence.entity.MissionEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface MissionRepository : CrudRepository<MissionEntity, UUID>