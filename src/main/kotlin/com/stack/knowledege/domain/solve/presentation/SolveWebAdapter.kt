package com.stack.knowledege.domain.solve.presentation

import com.stack.knowledege.domain.mission.presentation.data.request.SolveMissionRequest
import com.stack.knowledege.domain.solve.application.usecase.SolveMissionUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/solve")
class SolveWebAdapter(
    private val solveMissionUseCase: SolveMissionUseCase
) {
    @PostMapping("/{mission_id}")
    fun solveMission(@PathVariable("mission_id") missionId: UUID, @RequestBody solveMissionRequest: SolveMissionRequest): ResponseEntity<Void> =
        solveMissionUseCase.execute(missionId, solveMissionRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}