package com.stack.knowledge.domain.time.application.spi

import com.stack.knowledge.domain.mission.domain.Mission
import com.stack.knowledge.domain.student.domain.Student
import com.stack.knowledge.domain.time.domain.Time

interface QueryTimePort {
    fun queryTimeByMissionAndStudent(mission: Mission, student: Student): Time?
}