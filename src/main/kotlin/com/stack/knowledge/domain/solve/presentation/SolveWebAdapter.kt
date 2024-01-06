package com.stack.knowledge.domain.solve.presentation

import com.stack.knowledge.domain.solve.application.service.SolveMissionService
import com.stack.knowledge.domain.solve.presentation.data.request.SolveMissionRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/solve")
class SolveWebAdapter(
    private val solveMissionService: SolveMissionService
) {
    @PostMapping("/{mission_id}")
    fun solveMission(@PathVariable("mission_id") missionId: UUID, @RequestBody @Valid solveMissionRequest: SolveMissionRequest): ResponseEntity<Unit> =
        solveMissionService.execute(missionId, solveMissionRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}