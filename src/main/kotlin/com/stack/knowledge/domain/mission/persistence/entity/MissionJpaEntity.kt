package com.stack.knowledge.domain.mission.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import com.stack.knowledge.domain.mission.domain.constant.MissionStatus
import com.stack.knowledge.domain.user.persistence.entity.UserJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "mission")
class MissionJpaEntity(
//
//    override var id: UUID,

    @Column(nullable = false, length = 50)
    val title: String,

    @Column(nullable = false, length = 500)
    val content: String,

    @Column(nullable = false)
    val timeLimit: Int,

    @Column(nullable = false)
    val point: Int,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val missionStatus: MissionStatus,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    val user: UserJpaEntity

) : BaseIdEntity()