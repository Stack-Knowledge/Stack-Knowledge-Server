package com.stack.knowledge.domain.solve.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.solve.domain.constant.SolveStatus
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "solve")
class SolveJpaEntity(
    @get:JvmName(name = "getIdentifier")
    override var id: UUID,

    @Column(nullable = false, length = 500)
    val solution: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val solveStatus: SolveStatus,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    val mission: MissionJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity

) : BaseIdEntity(id)