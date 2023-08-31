package com.stack.knowledge.domain.student.persistence.entity

import com.stack.knowledge.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledge.common.entity.BaseIdEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "student")
class StudentJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val currentPoint: Int,

    @Column(nullable = false)
    val cumulatePoint: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity

) : BaseIdEntity(id)