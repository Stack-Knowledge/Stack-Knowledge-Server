package com.stack.knowledge.domain.user.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.domain.constant.Authority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class UserJpaEntity(

    override val id: UUID,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = true)
    val profileImage: String? = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val authority: Authority,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val approveStatus: ApproveStatus

) : BaseIdEntity(id)