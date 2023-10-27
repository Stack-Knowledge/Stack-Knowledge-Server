package com.stack.knowledge.domain.order.application.spi

import com.stack.knowledge.domain.order.domain.Order
import com.stack.knowledge.domain.student.domain.Student
import java.util.UUID

interface QueryOrderPort {
    fun queryOrderById(orderId: UUID): Order?
    fun queryAllOrderByCreatedAt(): List<Order>
    fun queryAllByStudent(student: Student): List<Order>
}