package com.stack.knowledege.domain.point.persistence.entity

import com.stack.knowledege.common.entity.BaseIdEntity
import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "point")
class PointJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val missionPoint: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    val mission: MissionJpaEntity

) : BaseIdEntity(id)