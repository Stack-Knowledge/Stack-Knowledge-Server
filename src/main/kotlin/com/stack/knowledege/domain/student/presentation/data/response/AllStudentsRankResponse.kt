package com.stack.knowledege.domain.student.presentation.data.response

import java.util.UUID

data class AllStudentsRankResponse(
    val id: UUID,
    val email: String,
    val name: String,
    val profileImage: String?,
    val point: Int
)