package com.stack.knowledge.common.entity

import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseIdEntity(
    @Id
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    @get:JvmName(name = "getIdentifier")
    open var id: UUID = UUID(0, 0)
) : BaseTimeEntity(), Persistable<UUID> {

    override fun getId(): UUID? = id

    override fun isNew(): Boolean = (id == UUID(0,0)).also { new ->
        if (new) id = UUID.randomUUID()
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    override fun hashCode() = Objects.hashCode(id)

    private fun getIdentifier(obj: Any): Serializable =
        if (obj is HibernateProxy) obj.hibernateLazyInitializer.identifier
        else (obj as BaseIdEntity).id

}