package com.stack.knowledge.domain.item.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "item")
class ItemJpaEntity(

    override val id: UUID,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val image: String

) : BaseIdEntity(id)