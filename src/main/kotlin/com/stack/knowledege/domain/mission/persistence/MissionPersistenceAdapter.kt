package com.stack.knowledege.domain.mission.persistence

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.mission.persistence.mapper.MissionMapper
import com.stack.knowledege.domain.mission.persistence.repository.MissionRepository
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.persistence.mapper.UserMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class MissionPersistenceAdapter(
    private val missionRepository: MissionRepository,
    private val missionMapper: MissionMapper,
    private val userMapper: UserMapper
) : MissionPort {
    override fun queryAllMission(): List<Mission> =
        missionRepository.findAll().map { missionMapper.toDomain(it)!! }

    override fun queryMissionById(missionId: UUID): Mission? =
        missionMapper.toDomain(missionRepository.findByIdOrNull(missionId))

    override fun queryMissionByUser(user: User): Mission? =
        missionMapper.toDomain(missionRepository.findByUser(userMapper.toEntity(user)))

    override fun queryMissionByMissionStatus(missionStatus: MissionStatus): List<Mission> =
        missionRepository.findByMissionStatus(missionStatus).map { missionMapper.toDomain(it)!! }

    override fun save(mission: Mission) {
        missionMapper.toDomain(missionRepository.save(missionMapper.toEntity(mission)))
    }
}