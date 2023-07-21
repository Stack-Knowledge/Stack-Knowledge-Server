package com.stack.knowledege.domain.item.persistence

import com.stack.knowledege.domain.item.application.spi.ItemPort
import com.stack.knowledege.domain.item.domain.Item
import com.stack.knowledege.domain.item.persistence.mapper.ItemMapper
import com.stack.knowledege.domain.item.persistence.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class ItemPersistenceAdapter(
    private val itemRepository: ItemRepository,
    private val itemMapper: ItemMapper
) : ItemPort {
    override fun queryAllItem(): List<Item> =
        itemRepository.findAll().map { itemMapper.toDomain(it)!! }
}