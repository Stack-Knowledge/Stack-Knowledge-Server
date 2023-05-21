package com.stack.knowledege.domain.mission.persistence.entity

import com.stack.knowledege.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class MissionEntity(

    override val id: UUID,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val content: String,

    @Column(nullable = false)
    val duration: Int,

    @Column(nullable = false)
    val timeLimit: Int,

    @Column(nullable = false)
    val isSolved: Boolean

) : BaseUuidEntity(id)