package com.stack.knowledege.domain.point.application.spi

import com.stack.knowledege.domain.mission.domain.Mission
import com.stack.knowledege.domain.point.domain.Point
import com.stack.knowledege.domain.solve.domain.Solve

interface QueryPointPort {
    fun queryPointByMission(mission: Mission): Point?
    fun queryPointBySolve(solve: Solve): Point?
    fun queryPointTopByIdDesc(): Point?
}