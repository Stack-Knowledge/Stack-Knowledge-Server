package com.stack.knowledege.domain.student.persistence.entity

import com.stack.knowledege.domain.user.persistence.entity.UserEntity
import com.stack.knowledege.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class StudentJpaEntity(

    override val id: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Column(nullable = false)
    val grade: Int,

    @Column(nullable = false)
    val classes: Int,

    @Column(nullable = false)
    val number: Int,

    @Column(nullable = false)
    val point: Int

) : BaseUuidEntity(id)