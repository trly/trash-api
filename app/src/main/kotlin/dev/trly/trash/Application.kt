package dev.trly.trash

import dev.trly.trash.routes.trashBinRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.openapi.openAPI
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    install(StatusPages) {
        // Global error handling can be added here
    }

    configureRouting()
}

fun Application.configureRouting() {
    routing {
        openAPI(path = "openapi", swaggerFile = "openapi/documentation.yaml")
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
        trashBinRoutes()
    }
}
