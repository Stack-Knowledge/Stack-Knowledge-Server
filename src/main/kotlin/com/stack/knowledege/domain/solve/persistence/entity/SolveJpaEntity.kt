package com.stack.knowledege.domain.solve.persistence.entity

import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import com.stack.knowledege.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledege.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class SolveJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val solvation: String,

    @Column(nullable = false)
    val solveStatus: SolveStatus,

    @ManyToOne
    @JoinColumn(name = "mission_id")
    val mission: MissionJpaEntity,

    @ManyToOne
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity

) : BaseUuidEntity(id)