package com.stack.knowledge.domain.solve.application.spi

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import java.util.*

interface QuerySolvePort {
    fun queryAllSolveBySolveStatusAndMissionOrderByCreatedAtDesc(solveStatus: SolveStatus, mission: Mission): List<Solve>
    fun querySolveById(solveId: UUID): Solve?
    fun queryAllSolveByStudentId(studentId: UUID): List<Solve>
    fun existByStudentIdAndMissionId(studentId: UUID, missionId: UUID): Boolean
}