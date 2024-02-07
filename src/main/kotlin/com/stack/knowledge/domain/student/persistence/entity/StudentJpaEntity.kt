package com.stack.knowledge.domain.student.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import com.stack.knowledge.domain.user.persistence.entity.UserJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "student")
class StudentJpaEntity(
    @get:JvmName(name = "getIdentifier")
    override var id: UUID,

    @Column(nullable = false)
    val currentPoint: Int,

    @Column(nullable = false)
    val cumulatePoint: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity

) : BaseIdEntity(id)