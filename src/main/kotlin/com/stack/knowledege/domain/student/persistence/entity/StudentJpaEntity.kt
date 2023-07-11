package com.stack.knowledege.domain.student.persistence.entity

import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledege.global.entity.BaseIdEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class StudentJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val point: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity

) : BaseIdEntity(id)