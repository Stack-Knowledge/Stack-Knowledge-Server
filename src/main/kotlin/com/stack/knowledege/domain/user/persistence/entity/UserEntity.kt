package com.stack.knowledege.domain.user.persistence.entity

import com.stack.knowledege.domain.user.domain.constant.UserRole
import com.stack.knowledege.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.*

@Entity
class UserEntity(

    override val id: UUID,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val grade: Int,

    @Column(nullable = false)
    val number: Int,

    @Column(nullable = false)
    val point: Int,

    @Column(nullable = true)
    val profileImage: String?,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "UerRole", joinColumns = [JoinColumn(name = "user_id")])
    val roles: MutableList<UserRole> = mutableListOf()

) : BaseUuidEntity(id)