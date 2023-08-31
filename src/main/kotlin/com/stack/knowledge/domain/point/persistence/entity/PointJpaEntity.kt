package com.stack.knowledge.domain.point.persistence.entity

import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
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
    val mission: MissionJpaEntity

)