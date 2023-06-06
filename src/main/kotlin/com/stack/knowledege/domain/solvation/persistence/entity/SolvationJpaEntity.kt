package com.stack.knowledege.domain.solvation.persistence.entity

import com.stack.knowledege.domain.mission.persistence.entity.MissionEntity
import com.stack.knowledege.domain.user.persistence.entity.UserEntity
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
    val mission: MissionEntity,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity

) : BaseUuidEntity(id)