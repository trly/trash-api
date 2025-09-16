package dev.trly.trash.routes

import dev.trly.trash.service.TrashBinService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.trashBinRoutes() {
    val trashBinService = TrashBinService()

    route("/trash-bins") {
        get {
            val trashBin = trashBinService.generateRandomTrashBin()
            call.respond(HttpStatusCode.OK, trashBin)
        }

        get("/random") {
            val countParam = call.request.queryParameters["count"]
            val count = countParam?.toIntOrNull() ?: 1

            if (count <= 0 || count > 100) {
                call.respond(HttpStatusCode.BadRequest, "Count must be between 1 and 100")
                return@get
            }

            val trashBins = trashBinService.generateRandomTrashBins(count)
            call.respond(HttpStatusCode.OK, trashBins)
        }
    }
}
