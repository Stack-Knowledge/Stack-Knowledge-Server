package com.stack.knowledege.domain.mission.application.usecase

import com.stack.knowledege.domain.mission.application.spi.CommandMissionPort
import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.mission.domain.constant.MissionStatus
import com.stack.knowledege.domain.mission.presentation.data.request.CreateMissionRequest
import com.stack.knowledege.domain.user.application.validator.UserValidator
import com.stack.knowledege.common.annotation.usecase.UseCase
import com.stack.knowledege.common.service.SecurityService
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.security.exception.InvalidRoleException
import java.time.LocalTime
import java.util.*

@UseCase
class CreateMissionUseCase(
    private val commandMissionPort: CommandMissionPort,
    private val userValidator: UserValidator,
    private val securityService: SecurityService,
    private val queryStudentPort: QueryStudentPort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(createMissionRequest: CreateMissionRequest) {
        val user = when (securityService.queryCurrentUserAuthority()) {
            Authority.ROLE_STUDENT.name -> {
                val student = queryStudentPort.queryStudentById(securityService.queryCurrentUserId()) ?: throw UserNotFoundException()
                queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()
            }
            Authority.ROLE_TEACHER.name -> {
                queryUserPort.queryUserById(securityService.queryCurrentUserId()) ?: throw UserNotFoundException()
            }
            else -> throw InvalidRoleException()
        }

        userValidator.validateUserTeacher(user)

        val mission = Mission(
            id = UUID.randomUUID(),
            title = createMissionRequest.title,
            content = createMissionRequest.content,
            timeLimit = createMissionRequest.timeLimit,
            point = 1000,
            missionStatus = queryMissionStatusBasedOnTime(),
            userId = user.id
        )

        commandMissionPort.save(mission)
    }

    private fun queryMissionStatusBasedOnTime(): MissionStatus {
        val time = LocalTime.now()
        return if (time in LocalTime.of(12, 30)..LocalTime.of(19, 30)) {
            MissionStatus.OPENED
        } else {
            MissionStatus.AVAILABLE_OPEN
        }
    }
}