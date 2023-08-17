package com.stack.knowledege.domain.point.domain

import java.util.UUID

data class Point(
    val id: UUID,
    val missionPoint: Int,
    val mission: UUID
)