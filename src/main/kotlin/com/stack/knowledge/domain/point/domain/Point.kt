package com.stack.knowledge.domain.point.domain

import java.util.UUID

data class Point(
    val id: Long = 0,
    val missionPoint: Int,
    val mission: UUID
)