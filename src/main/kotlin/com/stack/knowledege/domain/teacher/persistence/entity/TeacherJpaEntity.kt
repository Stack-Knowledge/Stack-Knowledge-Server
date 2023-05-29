package com.stack.knowledege.domain.teacher.persistence.entity

import com.stack.knowledege.domain.user.persistence.entity.UserEntity
import com.stack.knowledege.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToOne

@Entity
class TeacherJpaEntity(

    override val id: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    val user: UserEntity,

    @Column(nullable = false)
    val subject: String

) : BaseUuidEntity(id)