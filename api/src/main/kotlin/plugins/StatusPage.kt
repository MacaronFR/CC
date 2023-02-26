package plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import ports.NotFoundException
import ports.out.exception.NoTokenLeftException

fun Application.statusPage(){
	install(StatusPages) {
		exception<Throwable> { call, cause ->
			when(cause){
				is NotFoundException -> call.respondText("404: ${cause.message}", status = HttpStatusCode.NotFound)
				is NoTokenLeftException -> call.respondText("400: No more token left", status = HttpStatusCode.BadRequest)
				is BadRequestException -> call.respondText("400: Invalid input", status = HttpStatusCode.BadRequest)
				else -> {
					cause.printStackTrace()
					call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
				}
			}
		}
	}
}