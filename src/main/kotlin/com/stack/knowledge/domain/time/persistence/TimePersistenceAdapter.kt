package com.stack.knowledge.domain.time.persistence

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.mission.persistence.mapper.MissionMapper
import com.stack.knowledge.domain.student.domain.Student
import com.stack.knowledge.domain.student.persistence.mapper.StudentMapper
import com.stack.knowledge.domain.time.application.spi.TimePort
import com.stack.knowledge.domain.time.domain.Time
import com.stack.knowledge.domain.time.persistence.mapper.TimeMapper
import com.stack.knowledge.domain.time.persistence.repository.TimeJpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class TimePersistenceAdapter(
    private val timeJpaRepository: TimeJpaRepository,
    private val timeMapper: TimeMapper,
    private val missionMapper: MissionMapper,
    private val studentMapper: StudentMapper
) : TimePort {
    override fun save(time: Time) {
        timeJpaRepository.save(timeMapper.toEntity(time))
    }

    override fun deleteAllByMissionIds(missionIds: List<UUID>) {
        timeJpaRepository.deleteAllByMissionIds(missionIds)
    }

    override fun queryTimeByMissionAndStudentId(mission: Mission, student: Student): Time? =
        timeMapper.toDomain(timeJpaRepository.findByMissionAndStudent(missionMapper.toEntity(mission), studentMapper.toEntity(student)))
}