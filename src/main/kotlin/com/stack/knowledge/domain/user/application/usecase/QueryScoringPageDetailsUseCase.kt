package com.stack.knowledge.domain.user.application.usecase

import com.stack.knowledge.common.annotation.usecase.ReadOnlyUseCase
import com.stack.knowledge.domain.mission.application.spi.QueryMissionPort
import com.stack.knowledge.domain.mission.exception.MissionNotFoundException
import com.stack.knowledge.domain.solve.application.spi.QuerySolvePort
import com.stack.knowledge.domain.solve.exception.SolveNotFoundException
import com.stack.knowledge.domain.user.presentation.data.response.ScoringDetailsResponse
import java.util.*

@ReadOnlyUseCase
class QueryScoringPageDetailsUseCase(
    private val querySolvePort: QuerySolvePort,
    private val queryMissionPort: QueryMissionPort
) {
    fun execute(solveId: UUID): ScoringDetailsResponse {
        val solve = querySolvePort.querySolveById(solveId) ?: throw SolveNotFoundException()
        val mission = queryMissionPort.queryMissionById(solve.mission) ?: throw MissionNotFoundException()

        return ScoringDetailsResponse(
            solveId = solveId,
            title = mission.title,
            solvation = solve.solvation
        )
    }
}