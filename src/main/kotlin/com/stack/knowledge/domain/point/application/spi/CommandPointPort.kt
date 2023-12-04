package com.stack.knowledge.domain.point.application.spi

import com.stack.knowledge.domain.point.domain.Point
import java.util.*

interface CommandPointPort {
    fun save(point: Point)
    fun deleteAllByMissionIds(missionIds: List<UUID>)
}