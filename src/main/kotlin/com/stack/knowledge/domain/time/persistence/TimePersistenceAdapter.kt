package com.stack.knowledge.domain.time.persistence

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.persistence.mapper.MissionMapper
import com.stack.knowledge.domain.time.application.spi.StudentPort
import com.stack.knowledge.domain.time.domain.Time
import com.stack.knowledge.domain.time.persistence.mapper.TimeMapper
import com.stack.knowledge.domain.time.persistence.repository.TimeJpaRepository
import org.springframework.stereotype.Component

@Component
class TimePersistenceAdapter(
    private val timeJpaRepository: TimeJpaRepository,
    private val timeMapper: TimeMapper,
    private val missionMapper: MissionMapper
) : StudentPort {
    override fun save(time: Time) {
        timeJpaRepository.save(timeMapper.toEntity(time))
    }

    override fun queryTimeByMission(mission: Mission): Time? =
        timeMapper.toDomain(timeJpaRepository.findByMissionJpaEntity(missionMapper.toEntity(mission)))
}