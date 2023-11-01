package com.stack.knowledge.domain.time.persistence.mapper

import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.persistence.repository.MissionJpaRepository
import com.stack.knowledge.domain.time.domain.Time
import com.stack.knowledge.domain.time.persistence.entity.TimeJpaEntity
import com.stack.knowledge.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class TimeMapper(
    private val missionJpaRepository: MissionJpaRepository
) : GenericMapper<Time, TimeJpaEntity> {
    override fun toDomain(entity: TimeJpaEntity?): Time? =
        entity?.let {
            Time(
                id = it.id,
                mission = it.missionJpaEntity.id,
                createdAt = entity.createdAt
            )
        }

    override fun toEntity(domain: Time): TimeJpaEntity {
        val mission = missionJpaRepository.findByIdOrNull(domain.mission) ?: throw MissionNotFoundException()

        return TimeJpaEntity(
            id = domain.id,
            missionJpaEntity = mission,
        )
    }
}