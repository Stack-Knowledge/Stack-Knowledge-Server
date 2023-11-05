package com.stack.knowledge.domain.time.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "time")
class TimeJpaEntity(

    override val id: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    val missionJpaEntity: MissionJpaEntity

) : BaseIdEntity(id)