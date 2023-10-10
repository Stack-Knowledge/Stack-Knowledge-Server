package com.stack.knowledge.domain.solve.application.spi

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import java.util.UUID

interface QuerySolvePort {
    fun queryAllSolveBySolveStatusAndMission(solveStatus: SolveStatus, mission: Mission): List<Solve>
    fun querySolveById(solveId: UUID): Solve?
    fun queryAllSolveByMission(mission: Mission): List<Solve>
    fun queryAllSolveByStudentId(studentId: UUID): List<Solve>
}