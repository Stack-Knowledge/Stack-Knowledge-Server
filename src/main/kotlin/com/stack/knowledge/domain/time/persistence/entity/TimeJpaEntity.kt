package com.stack.knowledge.domain.time.persistence.entity

import com.stack.knowledge.common.entity.BaseIdEntity
import com.stack.knowledge.domain.mission.persistence.entity.MissionJpaEntity
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "time")
class TimeJpaEntity(

    override val id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    val mission: MissionJpaEntity

) : BaseIdEntity(id)