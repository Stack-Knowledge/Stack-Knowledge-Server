package com.stack.knowledge.domain.mission.persistence.entity

import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledge.common.entity.BaseIdEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "mission")
class MissionJpaEntity(

    override val id: UUID,

    @Column(nullable = false, length = 20)
    val title: String,

    @Column(nullable = false)
    val content: String,

    @Column(nullable = false)
    val timeLimit: Int,

    @Column(nullable = false)
    val point: Int,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val missionStatus: MissionStatus,

    @ManyToOne
    @JoinColumn(name = "user_Id")
    val user: UserJpaEntity

) : BaseIdEntity(id)