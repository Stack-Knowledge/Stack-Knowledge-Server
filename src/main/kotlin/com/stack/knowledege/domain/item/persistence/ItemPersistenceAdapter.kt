package com.stack.knowledege.domain.item.persistence

import com.stack.knowledege.domain.item.application.spi.ItemPort
import com.stack.knowledege.domain.item.domain.Item
import com.stack.knowledege.domain.item.persistence.mapper.ItemMapper
import com.stack.knowledege.domain.item.persistence.repository.ItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class ItemPersistenceAdapter(
    private val itemRepository: ItemRepository,
    private val itemMapper: ItemMapper
) : ItemPort {
    override fun saveItem(item: Item): Item =
        itemMapper.toDomain(itemRepository.save(itemMapper.toEntity(item)))!!


    override fun deleteItem(itemId: UUID) =
        itemRepository.deleteById(itemId)

    override fun queryAllItem(): List<Item> =
        itemRepository.findAll().map { itemMapper.toDomain(it)!! }

    override fun queryItemById(itemId: UUID): Item? =
        itemMapper.toDomain(itemRepository.findByIdOrNull(itemId))
}