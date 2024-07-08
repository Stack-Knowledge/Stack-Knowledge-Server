package com.stack.knowledge.domain.student.persistence.repository

import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledge.domain.user.persistence.entity.UserJpaEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface StudentJpaRepository : CrudRepository<StudentJpaEntity, UUID> {
    @Query("SELECT s FROM StudentJpaEntity s JOIN FETCH s.user order by s.cumulatePoint DESC")
    fun findAllByOrderByCumulatePointDesc(): List<StudentJpaEntity>
    fun findByUserId(userId: UUID): StudentJpaEntity
    fun existsByUser(user: UserJpaEntity): Boolean
    @Query("SELECT COUNT(s) + 1 FROM StudentJpaEntity s WHERE s.cumulatePoint > (SELECT st.cumulatePoint FROM StudentJpaEntity st WHERE st.id = :id)")
    fun findStudentRankByCumulatePoint(id: UUID): Int
}