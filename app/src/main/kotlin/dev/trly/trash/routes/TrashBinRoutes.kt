package dev.trly.trash.routes

import dev.trly.trash.service.TrashBinService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
