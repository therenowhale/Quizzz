package org.quizzz

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform