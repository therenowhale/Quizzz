package org.quizzz

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.v1.jdbc.Database

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    createTables()

    environment.monitor.subscribe(ApplicationStopped)
    { DatabaseFactory.close() }

    routing {
        get("/") {
            call.respondText("Server and database configuration loaded")
        }
    }
}