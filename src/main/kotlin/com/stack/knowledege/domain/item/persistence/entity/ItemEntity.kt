package com.stack.knowledege.domain.item.persistence.entity

import com.stack.knowledege.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class ItemEntity(
    override val id: UUID,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val text: String,
    @Column(nullable = false)
    val price: Int
) : BaseUuidEntity(id)