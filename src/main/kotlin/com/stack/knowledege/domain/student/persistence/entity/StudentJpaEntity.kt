package com.stack.knowledege.domain.student.persistence.entity

import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import com.stack.knowledege.common.entity.BaseIdEntity
import com.stack.knowledege.domain.point.persistence.entity.PointJpaEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    val point: PointJpaEntity,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity

) : BaseIdEntity(id)