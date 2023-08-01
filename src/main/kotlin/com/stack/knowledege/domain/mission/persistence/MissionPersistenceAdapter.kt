package com.stack.knowledege.domain.mission.persistence

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.mission.persistence.mapper.MissionMapper
import com.stack.knowledege.domain.mission.persistence.repository.MissionJpaRepository
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.persistence.mapper.UserMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class MissionPersistenceAdapter(
    private val missionJpaRepository: MissionJpaRepository,
    private val missionMapper: MissionMapper,
    private val userMapper: UserMapper
) : MissionPort {
    override fun queryAllMission(): List<Mission> =
        missionJpaRepository.findAll().map { missionMapper.toDomain(it)!! }

    override fun queryMissionById(missionId: UUID): Mission? =
        missionMapper.toDomain(missionJpaRepository.findByIdOrNull(missionId))

    override fun queryMissionByUser(user: User): Mission? =
        missionMapper.toDomain(missionJpaRepository.findByUser(userMapper.toEntity(user)))

    override fun queryMissionByMissionStatus(missionStatus: MissionStatus): List<Mission> =
        missionJpaRepository.findByMissionStatus(missionStatus).map { missionMapper.toDomain(it)!! }

    override fun save(mission: Mission) {
        missionMapper.toDomain(missionJpaRepository.save(missionMapper.toEntity(mission)))
    }
}