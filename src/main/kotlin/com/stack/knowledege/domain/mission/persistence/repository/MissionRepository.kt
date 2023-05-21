package com.stack.knowledege.domain.mission.persistence.repository

import com.stack.knowledege.domain.mission.domain.Mission
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface MissionRepository : CrudRepository<Mission, UUID>