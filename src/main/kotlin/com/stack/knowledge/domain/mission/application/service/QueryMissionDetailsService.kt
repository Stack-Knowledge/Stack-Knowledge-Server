package com.stack.knowledge.domain.mission.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.mission.exception.MissionNotOpenedException
import com.stack.knowledge.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.time.application.spi.TimePort
import com.stack.knowledge.domain.time.domain.Time
import java.time.LocalDateTime
import java.util.*

@ServiceWithTransaction
class QueryMissionDetailsService(
    private val queryMissionPort: QueryMissionPort,
    private val timePort: TimePort,
    private val securityService: SecurityService,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(id: UUID): MissionDetailsResponse {
        val student = securityService.queryCurrentUserId().let {
            queryStudentPort.queryStudentById(it) ?: throw StudentNotFoundException()
        }
        val mission = queryMissionPort.queryMissionById(id) ?: throw MissionNotFoundException()

        if (mission.missionStatus != MissionStatus.OPENED)
            throw MissionNotOpenedException()

        timePort.queryTimeByMissionAndStudentId(mission, student)
            ?: timePort.save(
                Time(
                    id = UUID.randomUUID(),
                    student = student.id,
                    mission = mission.id,
                    createdAt = LocalDateTime.now()
                )
            )

        return MissionDetailsResponse(
            title = mission.title,
            content = mission.content,
            timeLimit = mission.timeLimit
        )
    }
}