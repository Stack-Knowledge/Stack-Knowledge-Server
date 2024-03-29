package com.stack.knowledge.domain.item.persistence

import com.stack.knowledge.domain.item.application.spi.ItemPort
import com.stack.knowledge.domain.item.domain.Item
import com.stack.knowledge.domain.item.persistence.mapper.ItemMapper
import com.stack.knowledge.domain.item.persistence.repository.ItemJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class ItemPersistenceAdapter(
    private val itemJpaRepository: ItemJpaRepository,
    private val itemMapper: ItemMapper
) : ItemPort {
    override fun queryAllItem(): List<Item> =
        itemJpaRepository.findAll().map { itemMapper.toDomain(it)!! }

    override fun queryItemById(itemId: UUID): Item? =
        itemMapper.toDomain(itemJpaRepository.findByIdOrNull(itemId))
}