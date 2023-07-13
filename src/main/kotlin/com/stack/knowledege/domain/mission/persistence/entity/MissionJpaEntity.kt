package com.stack.knowledege.domain.mission.persistence.entity

import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledege.global.entity.BaseIdEntity
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
    val timeLimit: Int,

    @ManyToOne
    @JoinColumn(name = "user_Id")
    val user: UserJpaEntity

) : BaseIdEntity(id)