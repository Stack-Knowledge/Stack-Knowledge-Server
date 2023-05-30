package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledege.domain.mission.presentation.data.response.MissionResponse
import com.stack.knowledege.domain.teacher.application.spi.QueryTeacherPort
import com.stack.knowledege.domain.teacher.presentation.data.response.TeacherResponse
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllMissionUseCase(
    private val queryMissionPort: QueryMissionPort,
    private val queryTeacherPort: QueryTeacherPort
) {
    fun execute(): List<MissionResponse> {
        val notices = queryMissionPort.queryAllMission()

        return notices.map{
            val teacher = queryTeacherPort.queryTeacherById(it.userId)
                ?: throw UserNotFoundException()

            MissionResponse(
                id = it.id,
                title = it.title,
                introduce = it.introduce,
                isSolved = it.isSolved,
                teacher = TeacherResponse(
                    id = teacher.id,
                    email = teacher.email,
                    name = teacher.name,
                    roles = teacher.roles,
                    subject = teacher.subject
                )
            )
        }
    }
}