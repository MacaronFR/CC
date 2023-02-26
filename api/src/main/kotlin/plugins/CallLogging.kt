package plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import org.slf4j.event.*

fun Application.callLogging() {
	install(CallLogging) {
		level = Level.INFO
	}
}