package com.stack.knowledege.domain.user.persistence.entity

import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.global.entity.BaseIdEntity
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
    val authority: Authority

) : BaseIdEntity(id)