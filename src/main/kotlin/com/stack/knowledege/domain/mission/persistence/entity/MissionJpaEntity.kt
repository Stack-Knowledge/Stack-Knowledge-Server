package com.stack.knowledege.domain.mission.persistence.entity

import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledege.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class MissionJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false, length = 15)
    val introduce: String,

    @Column(nullable = false)
    val content: String,

    @Column(nullable = false)
    val duration: Int,

    @Column(nullable = false)
    val timeLimit: Int,

    @Column(nullable = false)
    val isSolved: Boolean,

    @ManyToOne
    @JoinColumn(name = "user_Id")
    val user: UserJpaEntity

) : BaseUuidEntity(id)