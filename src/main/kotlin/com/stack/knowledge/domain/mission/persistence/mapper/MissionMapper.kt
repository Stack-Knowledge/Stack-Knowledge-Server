package com.stack.knowledge.domain.mission.persistence.mapper

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.persistence.repository.UserJpaRepository
import com.stack.knowledge.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

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
//            id = domain.id,
            title = domain.title,
            content = domain.content,
            timeLimit = domain.timeLimit,
            point = domain.point,
            missionStatus = domain.missionStatus,
            user = user
        )
    }
}