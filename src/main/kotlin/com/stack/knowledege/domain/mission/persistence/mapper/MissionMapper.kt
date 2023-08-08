package com.stack.knowledege.domain.mission.persistence.mapper

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledege.domain.user.persistence.repository.UserJpaRepository
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import com.stack.knowledege.domain.user.exception.UserNotFoundException

@Component
class MissionMapper(
    private val userJpaRepository: UserJpaRepository
) : GenericMapper<Mission, MissionJpaEntity> {
    override fun toDomain(entity: MissionJpaEntity?): Mission? =
        entity?.let {
            Mission(
                id = it.id,
                title = it.title,
                content = it.content,
                timeLimit = it.timeLimit,
                point = it.point,
                missionStatus = it.missionStatus,
                userId = it.user.id
            )
        }

    override fun toEntity(domain: Mission): MissionJpaEntity {
        val user = userJpaRepository.findByIdOrNull(domain.userId) ?: throw UserNotFoundException()

        return MissionJpaEntity(
            id = domain.id,
            title = domain.title,
            content = domain.content,
            timeLimit = domain.timeLimit,
            point = domain.point,
            missionStatus = domain.missionStatus,
            user = user
        )
    }
}