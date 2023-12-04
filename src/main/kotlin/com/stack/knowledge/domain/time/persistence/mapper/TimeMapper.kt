package com.stack.knowledge.domain.time.persistence.mapper

import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.persistence.repository.MissionJpaRepository
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledge.domain.time.domain.Time
import com.stack.knowledge.domain.time.persistence.entity.TimeJpaEntity
import com.stack.knowledge.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class TimeMapper(
    private val missionJpaRepository: MissionJpaRepository,
    private val studentJpaRepository: StudentJpaRepository
) : GenericMapper<Time, TimeJpaEntity> {
    override fun toDomain(entity: TimeJpaEntity?): Time? =
        entity?.let {
            Time(
                id = it.id,
                student = it.student.id,
                mission = it.mission.id,
                createdAt = entity.createdAt
            )
        }

    override fun toEntity(domain: Time): TimeJpaEntity {
        val mission = missionJpaRepository.findByIdOrNull(domain.mission) ?: throw MissionNotFoundException()
        val student = studentJpaRepository.findByIdOrNull(domain.student) ?: throw StudentNotFoundException()

        return TimeJpaEntity(
            id = domain.id,
            student = student,
            mission = mission,
        )
    }
}