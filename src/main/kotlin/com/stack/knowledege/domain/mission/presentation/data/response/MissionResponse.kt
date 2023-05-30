package com.stack.knowledege.domain.mission.presentation.data.response

import com.stack.knowledege.domain.teacher.presentation.data.response.TeacherResponse
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import java.util.*

class MissionResponse(
    val id: UUID,
    val title: String,
    val introduce: String,
    val isSolved: Boolean,
    val teacher: TeacherResponse
)