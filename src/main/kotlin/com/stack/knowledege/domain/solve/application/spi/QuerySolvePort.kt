package com.stack.knowledege.domain.solve.application.spi

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.solve.domain.Solve
import java.util.UUID

interface QuerySolvePort {
    fun queryAllSolvation(): List<Solve>
    fun querySolvationById(solvationId: UUID): Solve?
    fun querySolvationByMission(mission: Mission): Solve?
}