package com.stack.knowledege.domain.order.persistence.mapper

import com.stack.knowledege.domain.item.exception.ItemNotFoundException
import com.stack.knowledege.domain.item.persistence.repository.ItemJpaRepository
import com.stack.knowledege.domain.order.domain.Order
import com.stack.knowledege.domain.order.persistence.entity.OrderJpaEntity
import com.stack.knowledege.domain.student.exception.StudentNotFoundException
import com.stack.knowledege.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledege.global.mapper.GenericMapper
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