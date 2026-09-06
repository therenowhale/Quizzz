package org.quizzz

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.network.sockets.BoundDatagramSocket
import org.jetbrains.exposed.v1.jdbc.Database
import kotlin.math.max

object DatabaseFactory {
    private lateinit var dataSource: HikariDataSource

    fun init() {
        val host = System.getenv("DB_HOST") ?: "localhost"
        val port = System.getenv("DB_PORT") ?: "5432"
        val name = System.getenv("DB_NAME") ?: "quizzz"
        val user = System.getenv("DB_USER") ?: "quizzz_app"
        val password = System.getenv("DB_PASSWORD")
            ?: error("DB_PASSWORD not defined")

        dataSource = HikariDataSource(
            HikariConfig().apply {
                jdbcUrl = "jdbc:postgresql://$host:$port/$name"
                username = user
                this.password = password
                driverClassName = "org.postgresql.Driver"
                maximumPoolSize = 10
                minimumIdle = 2
            }
        )

        Database.connect(dataSource)
    }

    fun close() {
        dataSource.close()
    }
}
