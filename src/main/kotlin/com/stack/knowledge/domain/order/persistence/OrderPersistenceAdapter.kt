package com.stack.knowledge.domain.order.persistence

import com.stack.knowledge.domain.order.application.spi.OrderPort
import com.stack.knowledge.domain.order.domain.Order
import com.stack.knowledge.domain.order.persistence.mapper.OrderMapper
import com.stack.knowledge.domain.order.persistence.repository.OrderJpaRepository
import com.stack.knowledge.domain.student.domain.Student
import com.stack.knowledge.domain.student.persistence.mapper.StudentMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OrderPersistenceAdapter(
    private val orderJpaRepository: OrderJpaRepository,
    private val orderMapper: OrderMapper,
    private val studentMapper: StudentMapper
) : OrderPort {
    override fun save(order: Order) {
        orderJpaRepository.save(orderMapper.toEntity(order))
    }

    override fun delete(order: Order) {
        orderJpaRepository.delete(orderMapper.toEntity(order))
    }

    override fun queryOrderById(orderId: UUID): Order? =
        orderMapper.toDomain(orderJpaRepository.findByIdOrNull(orderId))

    override fun queryAllItem(): List<Order> =
        orderJpaRepository.findAll().map { orderMapper.toDomain(it)!! }

    override fun queryAllByStudent(student: Student): List<Order> =
        orderJpaRepository.findAllByStudent(studentMapper.toEntity(student)).map { orderMapper.toDomain(it)!! }
}