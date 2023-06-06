package com.stack.knowledege.domain.solvation.application.spi

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.solvation.domain.Solvation
import java.util.UUID

interface QuerySolvationPort {
    fun queryAllSolvation(): List<Solvation>
    fun querySolvationById(solvationId: UUID): Solvation?
    fun querySolvationByMission(mission: Mission): Solvation?
}