package com.stack.knowledege.domain.item.persistence.repository

import com.stack.knowledege.domain.item.persistence.entity.ItemJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ItemRepository : CrudRepository<ItemJpaEntity, UUID>