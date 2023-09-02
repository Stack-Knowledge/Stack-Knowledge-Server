package com.stack.knowledge.domain.item.persistence.repository

import com.stack.knowledge.domain.item.persistence.entity.ItemJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ItemJpaRepository : CrudRepository<ItemJpaEntity, UUID>