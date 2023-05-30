package com.stack.knowledege.domain.mission.presentation.data.response

import com.stack.knowledege.domain.teacher.presentation.data.response.TeacherResponse

class MissionDetailsResponse(
    val title: String,
    val content: String,
    val timeLimit: Int,
    val teacher: TeacherResponse
)