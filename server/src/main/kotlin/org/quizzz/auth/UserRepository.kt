package org.quizzz.repository

class UserRepository {
    
    fun findUserOrEmail(value: String): UserRecord? =
        transaction {
            UserTable
                .selectAll()
                .where {
                    (UserTable.username eq value) or (UserTable.email eq value)
                }
                .singleOrNull()
                ?.let { row -> 
                    UserRecord(
                        id = row[UserTable.id],
                        username = row[UserTable.username],
                        email = row[UserTable.email],
                        password = row[UserTable.password],
                        role = row[UserTable.role]
                    )
                }
        }
}
