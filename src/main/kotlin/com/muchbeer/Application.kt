package com.muchbeer

import com.muchbeer.route.module
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {

val portNumber = System.getenv("PORT")?.toInt() ?: 8080
val localhost = "192.168.31.23"
embeddedServer(Netty, port = portNumber) {


    install(ContentNegotiation) {
        jackson()
    }

    module()

    }.start(wait = true)
}

