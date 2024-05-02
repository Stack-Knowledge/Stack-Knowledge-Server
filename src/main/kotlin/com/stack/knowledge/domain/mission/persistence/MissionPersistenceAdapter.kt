package com.stack.knowledge.domain.mission.persistence

import com.stack.knowledge.domain.mission.application.spi.MissionPort
import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.persistence.mapper.MissionMapper
import com.stack.knowledge.domain.mission.persistence.repository.MissionJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class MissionPersistenceAdapter(
    private val missionJpaRepository: MissionJpaRepository,
    private val missionMapper: MissionMapper
) : MissionPort {
    override fun save(mission: Mission) {
        missionJpaRepository.save(missionMapper.toEntity(mission))
    }

    override fun queryMissionById(missionId: UUID): Mission? =
        missionMapper.toDomain(missionJpaRepository.findByIdOrNull(missionId))

    override fun queryAllMissionByMissionStatus(missionStatus: MissionStatus): List<Mission> =
        missionJpaRepository.findAllByMissionStatus(missionStatus).map { missionMapper.toDomain(it)!! }

    override fun queryAllMissionsByUserIdOrderByCreatedAtDesc(userId: UUID): List<Mission> =
        missionJpaRepository.findAllByUserIdOrderByCreatedAtDesc(userId).map { missionMapper.toDomain(it)!! }

    override fun queryAllMissionByMissionStatusOrderByCreatedAtDesc(missionStatus: MissionStatus): List<Mission> =
        missionJpaRepository.findAllByMissionStatusOrderByCreatedAtDesc(missionStatus).map { missionMapper.toDomain(it)!! }
}