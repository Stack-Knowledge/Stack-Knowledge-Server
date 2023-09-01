package com.stack.knowledge.domain.mission.persistence

import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.persistence.mapper.MissionMapper
import com.stack.knowledge.domain.mission.persistence.repository.MissionJpaRepository
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.persistence.mapper.UserMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class MissionPersistenceAdapter(
    private val missionJpaRepository: MissionJpaRepository,
    private val missionMapper: MissionMapper,
    private val userMapper: UserMapper
) : MissionPort {
    override fun save(mission: Mission) {
        missionMapper.toDomain(missionJpaRepository.save(missionMapper.toEntity(mission)))
    }

    override fun queryMissionById(missionId: UUID): Mission? =
        missionMapper.toDomain(missionJpaRepository.findByIdOrNull(missionId))

    override fun queryMissionByUser(user: User): Mission? =
        missionMapper.toDomain(missionJpaRepository.findByUser(userMapper.toEntity(user)))

    override fun queryAllMissionByMissionStatus(missionStatus: MissionStatus): List<Mission> =
        missionJpaRepository.findByMissionStatus(missionStatus).map { missionMapper.toDomain(it)!! }
}