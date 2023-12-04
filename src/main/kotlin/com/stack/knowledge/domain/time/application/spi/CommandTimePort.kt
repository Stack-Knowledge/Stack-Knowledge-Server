package com.stack.knowledge.domain.time.application.spi

import com.stack.knowledge.domain.time.domain.Time
import java.util.*

interface CommandTimePort {
    fun save(time: Time)
    fun deleteAllByMissionIds(missionIds: List<UUID>)
}