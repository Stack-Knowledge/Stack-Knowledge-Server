package com.stack.knowledge.domain.solve.application.spi

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.solve.domain.Solve
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import org.springframework.data.domain.Pageable
import java.util.UUID

interface QuerySolvePort {
    fun queryAllSolveBySolveStatus(solveStatus: SolveStatus, pageable: Pageable): List<Solve>
    fun querySolveById(solveId: UUID): Solve?
    fun querySolveByMission(mission: Mission): Solve?
    fun querySolveByStudentId(studentId: UUID): List<Solve>
}