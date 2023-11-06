package com.stack.knowledge.domain.point.application.spi

import com.stack.knowledge.domain.point.domain.Point
import com.stack.knowledge.domain.solve.domain.Solve
import java.util.*

interface QueryPointPort {
    fun queryPointBySolve(solve: Solve): Point?
    fun queryTopByMissionIdOrderByMissionPointAsc(missionId: UUID): Point?
}