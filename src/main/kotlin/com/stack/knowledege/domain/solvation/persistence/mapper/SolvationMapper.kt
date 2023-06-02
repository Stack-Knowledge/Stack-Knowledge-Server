package com.stack.knowledege.domain.solvation.persistence.mapper

import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.persistence.repository.MissionRepository
import com.stack.knowledege.domain.solvation.domain.Solvation
import com.stack.knowledege.domain.solvation.persistence.entity.SolvationJpaEntity
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SolvationMapper(
    private val missionRepository: MissionRepository,
) : GenericMapper<Solvation, SolvationJpaEntity> {
    override fun toDomain(entity: SolvationJpaEntity?): Solvation? =
        entity?.let {
            Solvation(
                id = it.id,
                answer = it.answer,
                mission = it.mission.id
            )
        }


    override fun toEntity(domain: Solvation): SolvationJpaEntity {
        val mission = missionRepository.findByIdOrNull(domain.mission) ?: throw MissionNotFoundException()
        return domain.let {
            SolvationJpaEntity(
                id = it.id,
                answer = it.answer,
                mission = mission
            )
        }
    }
}