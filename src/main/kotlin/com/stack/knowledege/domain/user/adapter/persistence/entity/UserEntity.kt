package com.stack.knowledege.domain.user.adapter.persistence.entity

import com.stack.knowledege.domain.user.adapter.persistence.constant.UserRole
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
    val grade: String,

    @Column(nullable = false)
    val number: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "UerRole", joinColumns = [JoinColumn(name = "user_id")])
    var roles: MutableList<UserRole> = mutableListOf()

): BaseUuidEntity(id)