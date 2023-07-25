package com.stack.knowledege.domain.item.persistence.entity

import com.stack.knowledege.global.entity.BaseIdEntity
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
    val price: Int

) : BaseIdEntity(id)