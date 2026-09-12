package org.quizzz

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello, Ktor!", response.bodyAsText())
    }
    
    @Test
    fun ivalidLogingReturnUnauthorized() = testApplication {
        application {
            module()
        }
        val response = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(
                usernameOrEmail = "invalidUser",
                password = "invalidPassword"
            ))
        }
        assertEquals(HttpStatusCode.Unauthorized, response.status)
    }
}