package com.stack.knowledege.domain.solvation.persistence.mapper

import com.stack.knowledege.domain.mission.exception.MissionNotFoundException
import com.stack.knowledege.domain.mission.persistence.repository.MissionRepository
import com.stack.knowledege.domain.solvation.domain.Solvation
import com.stack.knowledege.domain.solvation.persistence.entity.SolvationJpaEntity
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.user.persistence.repository.UserRepository
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SolvationMapper(
    private val missionRepository: MissionRepository,
    private val userRepository: UserRepository
) : GenericMapper<Solvation, SolvationJpaEntity> {
    override fun toDomain(entity: SolvationJpaEntity?): Solvation? =
        entity?.let {
            Solvation(
                id = it.id,
                answer = it.answer,
                user = it.user.id,
                mission = it.mission.id
            )
        }


    override fun toEntity(domain: Solvation): SolvationJpaEntity {
        val mission = missionRepository.findByIdOrNull(domain.mission) ?: throw MissionNotFoundException()
        val user = userRepository.findByIdOrNull(domain.user) ?: throw UserNotFoundException()
        return domain.let {
            SolvationJpaEntity(
                id = it.id,
                answer = it.answer,
                user = user,
                mission = mission
            )
        }
    }
}