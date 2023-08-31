package com.stack.knowledge.domain.item.persistence.mapper

import com.stack.knowledge.domain.item.domain.Item
import com.stack.knowledge.domain.item.persistence.entity.ItemJpaEntity
import com.stack.knowledge.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class ItemMapper : GenericMapper<Item, ItemJpaEntity> {
    override fun toDomain(entity: ItemJpaEntity?): Item? =
        entity?.let {
            Item(
                id = it.id,
                name = it.name,
                price = it.price
            )
        }


    override fun toEntity(domain: Item): ItemJpaEntity =
        domain.let {
            ItemJpaEntity(
                id = it.id,
                name = it.name,
                price = it.price
            )
        }
}