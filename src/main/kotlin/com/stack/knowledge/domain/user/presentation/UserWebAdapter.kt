package com.stack.knowledge.domain.user.presentation

import com.stack.knowledge.domain.user.application.usecase.QueryScoringPageUseCase
import com.stack.knowledge.domain.user.application.usecase.ScoreSolveUseCase
import com.stack.knowledge.domain.user.presentation.data.request.ScoreSolveRequest
import com.stack.knowledge.domain.user.presentation.data.response.AllScoringResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryScoringPageUseCase: QueryScoringPageUseCase,
    private val scoreSolveUseCase: ScoreSolveUseCase
) {
    @GetMapping("/scoring")
    fun queryAllSolve(): ResponseEntity<List<AllScoringResponse>> =
        queryScoringPageUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PostMapping("/scoring/{solve_id}")
    fun scoreSolve(@PathVariable("solve_id") solveId: UUID, @RequestBody @Valid scoreSolveRequest: ScoreSolveRequest): ResponseEntity<Void> =
        scoreSolveUseCase.execute(solveId, scoreSolveRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}