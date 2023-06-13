package com.stack.knowledege.domain.user.presentation.data.response

import java.util.*

data class AllUserRankResponse(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: Int,
    val number: Int,
    val point: Int,
    val profileImage: String?
)