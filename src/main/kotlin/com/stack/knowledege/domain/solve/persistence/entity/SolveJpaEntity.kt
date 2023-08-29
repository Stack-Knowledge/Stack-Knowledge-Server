package com.stack.knowledege.domain.solve.persistence.entity

import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledege.domain.solve.domain.constant.SolveStatus
import com.stack.knowledege.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledege.common.entity.BaseIdEntity
import com.stack.knowledege.domain.point.persistence.entity.PointJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "solve")
class SolveJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val solvation: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val solveStatus: SolveStatus,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    val mission: MissionJpaEntity,

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "point_id")
//    val point: PointJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity

) : BaseIdEntity(id)