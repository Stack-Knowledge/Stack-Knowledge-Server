package com.stack.knowledge.domain.order.persistence.entity

import com.stack.knowledge.domain.item.persistence.entity.ItemJpaEntity
import com.stack.knowledge.domain.order.domain.constant.OrderStatus
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledge.common.entity.BaseIdEntity
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "orders")
class OrderJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val count: Int,

    @Column(nullable = false)
    val price: Int,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val orderStatus: OrderStatus,

    @ManyToOne
    @JoinColumn(name = "item_id")
    val item: ItemJpaEntity,

    @ManyToOne
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity

) : BaseIdEntity(id)