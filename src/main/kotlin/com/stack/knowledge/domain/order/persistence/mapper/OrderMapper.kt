package com.stack.knowledge.domain.order.persistence.mapper

import com.stack.knowledge.domain.item.exception.ItemNotFoundException
import com.stack.knowledge.domain.item.persistence.repository.ItemJpaRepository
import com.stack.knowledge.domain.order.domain.Order
import com.stack.knowledge.domain.order.persistence.entity.OrderJpaEntity
import com.stack.knowledge.domain.student.exception.StudentNotFoundException
import com.stack.knowledge.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledge.global.mapper.GenericMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class OrderMapper(
    private val itemJpaRepository: ItemJpaRepository,
    private val studentJpaRepository: StudentJpaRepository
) : GenericMapper<Order, OrderJpaEntity> {
    override fun toDomain(entity: OrderJpaEntity?): Order? =
        entity?.let {
            Order(
                id = entity.id,
                count = entity.count,
                price = entity.price,
                orderStatus = entity.orderStatus,
                itemId = entity.item.id,
                studentId = entity.student.id
            )
        }

    override fun toEntity(domain: Order): OrderJpaEntity {
        val item = itemJpaRepository.findByIdOrNull(domain.itemId) ?: throw ItemNotFoundException()
        val student = studentJpaRepository.findByIdOrNull(domain.studentId) ?: throw StudentNotFoundException()

        return OrderJpaEntity(
            id = domain.id,
            count = domain.count,
            price = domain.price,
            orderStatus = domain.orderStatus,
            item = item,
            student = student
        )
    }
}