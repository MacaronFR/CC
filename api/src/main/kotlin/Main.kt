import io.ktor.server.application.*
import io.ktor.server.netty.*
import plugins.contentNegotiation
import plugins.resources

fun main(args: Array<String>){
	EngineMain.main(args)
}

fun Application.module(){
	contentNegotiation()
	resources()
}