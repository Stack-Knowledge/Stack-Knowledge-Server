package com.stack.knowledege.domain.solve.application.spi

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.solve.domain.Solve
import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import java.util.UUID

interface QuerySolvePort {
    fun queryAllSolveBySolveStatus(solveStatus: SolveStatus): List<Solve>
    fun querySolveById(solveId: UUID): Solve?
    fun querySolveByMission(mission: Mission): Solve?
    fun querySolveByStudentId(studentId: UUID): List<Solve>
}