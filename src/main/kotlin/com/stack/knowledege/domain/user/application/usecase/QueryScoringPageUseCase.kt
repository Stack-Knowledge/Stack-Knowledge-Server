package com.stack.knowledege.domain.user.application.usecase

import com.stack.knowledege.domain.solve.application.spi.QuerySolvePort
import com.stack.knowledege.domain.user.presentation.data.response.AllScoringResponse
import com.stack.knowledege.global.annotation.usecase.UseCase

@UseCase
class QueryScoringPageUseCase(
    private val querySolvePort: QuerySolvePort
) {
    fun execute(): List<AllScoringResponse> {
        val solves = querySolvePort.queryAllSolvation()

        return solves.map {
            AllScoringResponse(
                isSolved = it.isSolved,
                solveStatus = it.solveStatus,
                student = it.student
            )
        }
    }
}