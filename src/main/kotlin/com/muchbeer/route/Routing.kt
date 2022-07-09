package com.muchbeer.route

import com.muchbeer.repository.Repository
import com.muchbeer.repository.RepositoryImpl
import com.muchbeer.db.DatabaseFactory
import com.muchbeer.di.koinModule
import com.muchbeer.model.School
import com.muchbeer.model.USSDModel
import com.muchbeer.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.slf4j.event.Level

fun Application.configureMonitoring()  {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}

fun Application.koinDependency() {
    install(Koin) {
        modules(koinModule)
    }
}

fun Application.module() {

    configureMonitoring()
    koinDependency()

    // Lazy inject repository
    val repository : UserService by inject()
   // val dbConnect = DatabaseFactory.init()

   // val repository: Repository = RepositoryImpl(dbConnect)

    val listTem: List<School> = listOf(
        School(1, "Lubaga", "Shy", "Both"),
        School(1, "Loyola", "Dar", "Both")
    )


    routing {
        get("/") {

            call.respondText("USSD Project")
        }

        get("/gadiel") {
            call.response.headers.append("Content-Type", "application/json")
            call.respond(status = HttpStatusCode.OK, listTem)
        }
    }

    routing {

            CoroutineScope(Job()).launch {
        val retrieveSchool: List<School> = repository.retrieveAllSchool()

        get("/schools") {
            call.response.headers.append("Content-Type", "application/json")
            call.respond(status = HttpStatusCode.OK, retrieveSchool)
        }


        post("/register") {
            call.response.headers.append("Content-Type", "application/json")

            val addSchool = call.receiveOrNull<School>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }


            val response: School = repository.insertSchool(addSchool)

            call.respond(status = HttpStatusCode.OK, response)
        }

        post("/ussd") {

            call.response.headers.append("Content-Type", "application/json")
            val addUssd = call.receive<USSDModel>()

            if (repository.findUSSDSessionById(addUssd.sessionId) != null) {
                repository.updateSessionId(addUssd.sessionId, addUssd)
                call.respond(status = HttpStatusCode.OK, message = "Success ")
            } else {
                val response = repository.insertUSSD(addUssd)
                call.respond(status = HttpStatusCode.OK, response)
            }

        }
    }
    }
}