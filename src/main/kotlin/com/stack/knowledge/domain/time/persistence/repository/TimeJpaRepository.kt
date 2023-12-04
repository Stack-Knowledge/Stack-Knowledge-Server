package com.stack.knowledge.domain.time.persistence.repository

import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledge.domain.time.persistence.entity.TimeJpaEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TimeJpaRepository : CrudRepository<TimeJpaEntity, UUID> {
    fun findByMissionJpaEntityAndStudentJpaEntity(missionJpaEntity: MissionJpaEntity, studentJpaEntity: StudentJpaEntity): TimeJpaEntity?
    @Modifying
    @Query("delete from TimeJpaEntity t where t.missionJpaEntity in :missionIds")
    fun deleteAllByMissionJpaEntities(missionIds: List<UUID>)
}