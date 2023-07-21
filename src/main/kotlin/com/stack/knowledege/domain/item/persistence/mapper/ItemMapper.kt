package com.stack.knowledege.domain.item.persistence.mapper

import com.stack.knowledege.domain.item.domain.Item
import com.stack.knowledege.domain.item.persistence.entity.ItemJpaEntity
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class ItemMapper : GenericMapper<Item, ItemJpaEntity> {
    override fun toDomain(entity: ItemJpaEntity?): Item? =
        entity?.let {
            Item(
                id = it.id,
                name = it.name,
                text = it.text,
                price = it.price
            )
        }


    override fun toEntity(domain: Item): ItemJpaEntity =
        domain.let {
            ItemJpaEntity(
                id = it.id,
                name = it.name,
                text = it.text,
                price = it.price
            )
        }
}