package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.MissionPort
import com.stack.knowledege.domain.mission.exception.AlreadySolvedMissionException
import com.stack.knowledege.domain.mission.exception.NotFoundMissionException
import com.stack.knowledege.domain.mission.presentation.data.response.MissionDetailsResponse
import com.stack.knowledege.domain.teacher.application.spi.QueryTeacherPort
import com.stack.knowledege.domain.teacher.presentation.data.response.TeacherResponse
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryMissionDetailsUseCase(
    private val missionPort: MissionPort,
    private val queryTeacherPort: QueryTeacherPort
) {
    fun execute(id: UUID): MissionDetailsResponse {
        val mission = missionPort.queryMissionById(id)
            ?: throw NotFoundMissionException()
        val teacher = queryTeacherPort.queryTeacherById(mission.userId)
            ?: throw UserNotFoundException()

        if (mission.isSolved) {
            throw AlreadySolvedMissionException()
        }

        return MissionDetailsResponse(
            title = mission.title,
            content = mission.content,
            timeLimit = mission.timeLimit,
            teacher = TeacherResponse(
                id = UUID.randomUUID(),
                email = teacher.email,
                name = teacher.name,
                roles = teacher.roles,
                subject = teacher.subject
            )
        )
    }
}