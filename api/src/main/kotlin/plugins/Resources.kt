package plugins

import io.ktor.server.application.*
import io.ktor.server.resources.*

fun Application.resources(){
	install(Resources)
}