package com.stack.knowledge.domain.item.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "item")
class ItemJpaEntity(

    @get:JvmName(name = "getIdentifier")
    override var id: UUID,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val image: String

) : BaseIdEntity(id)