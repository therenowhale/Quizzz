package org.quizzz

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 50).uniqueIndex()
    val email = varchar("email", 50).uniqueIndex()
    val password = varchar("password_hash", 255)
    val role = varchar("role", 10)

    override val primaryKey = PrimaryKey(id)
}

fun createTables() {
    transaction {
        SchemaUtils.create(UserTable)
    }
}
