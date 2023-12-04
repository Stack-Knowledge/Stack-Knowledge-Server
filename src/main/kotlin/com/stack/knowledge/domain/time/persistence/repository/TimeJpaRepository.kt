package com.stack.knowledge.domain.time.persistence.repository

import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledge.domain.time.persistence.entity.TimeJpaEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TimeJpaRepository : CrudRepository<TimeJpaEntity, UUID> {
    fun findByMissionAndStudent(mission: MissionJpaEntity, student: StudentJpaEntity): TimeJpaEntity?
    @Modifying
    @Query("delete from TimeJpaEntity t where t.mission in :missionIds")
    fun deleteAllByMissionIds(missionIds: List<UUID>)
}