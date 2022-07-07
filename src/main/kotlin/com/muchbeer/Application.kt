package com.muchbeer

import com.muchbeer.db.DatabaseFactory
import com.muchbeer.model.School
import com.muchbeer.model.USSDModel
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {

val portNumber = System.getenv("PORT")?.toInt() ?: 8083
val localhost = "192.168.203.57"
embeddedServer(Netty, port = 8083, host =localhost) {


    install(ContentNegotiation) {
        jackson()
    }


    //  configureRouting(serviceUSSD)
    module()
    }.start(wait = true)
}

fun Application.module() {

    val dbConnect =   DatabaseFactory.init()

    val  repository : Repository = RepositoryImpl(dbConnect)

    val listTem : List<School> = listOf(
        School(1, "Lubaga", "Shy","Both"),
        School(1, "Loyola", "Dar", "Both")
    )

    val retrieveSchool : List<School> = repository.retrieveAllSchool()
    routing {
        get("/") {

            call.respondText("USSD Project")
        }

        get("/gadiel") {

            call.respond(listTem)
        }
        get("/schools") {

            call.respond(retrieveSchool)
        }
    }

    routing {


        post("/register") {
            val addSchool = call.receive<School>()
            val response : School = repository.insertSchool(addSchool)

            call.respond("$response")
        }

        post("/ussd") {


            val addUssd = call.receive<USSDModel>()

            if (repository.findUSSDSessionById(addUssd.sessionId) != null) {
                repository.updateSessionId(addUssd.sessionId, addUssd)
                call.respond(message = "Success ")
            } else {
                val response = repository.insertUSSD(addUssd)
                call.respond(response)
              }

        }

    }
}