package com.stack.knowledege.domain.point.persistence.entity

import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledege.domain.solve.persistence.entity.SolveJpaEntity
import javax.persistence.*

@Entity
@Table(name = "point")
class PointJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val missionPoint: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    val mission: MissionJpaEntity,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solve_id")
    val solve: SolveJpaEntity

)