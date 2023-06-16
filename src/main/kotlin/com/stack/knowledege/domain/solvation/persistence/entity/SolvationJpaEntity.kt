package com.stack.knowledege.domain.solvation.persistence.entity

import com.stack.knowledege.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledege.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class SolvationJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val answer: String,

    @ManyToOne
    @JoinColumn(name = "mission_id")
    val mission: MissionJpaEntity,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity

) : BaseUuidEntity(id)