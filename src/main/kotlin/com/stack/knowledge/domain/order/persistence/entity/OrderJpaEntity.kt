package com.stack.knowledge.domain.order.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import com.stack.knowledge.domain.item.persistence.entity.ItemJpaEntity
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "orders")
class OrderJpaEntity(
    @get:JvmName(name = "getIdentifier")
    override var id: UUID,

    @Column(nullable = false)
    val count: Int,

    @Column(nullable = false)
    val price: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: ItemJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity

) : BaseIdEntity(id)