package com.stack.knowledege.domain.item.persistence.mapper

import com.stack.knowledege.domain.item.domain.Item
import com.stack.knowledege.domain.item.persistence.entity.ItemEntity
import com.stack.knowledege.domain.item.persistence.repository.ItemRepository
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class ItemMapper(
    private val itemRepository: ItemRepository
) : GenericMapper<Item, ItemEntity> {
    override fun toDomain(entity: ItemEntity?): Item? =
        entity?.let {
            Item(
                id = it.id,
                name = it.name,
                text = it.text,
                price = it.price
            )
        }


    override fun toEntity(domain: Item): ItemEntity =
        domain.let {
            ItemEntity(
                id = it.id,
                name = it.name,
                text = it.text,
                price = it.price
            )
        }
}