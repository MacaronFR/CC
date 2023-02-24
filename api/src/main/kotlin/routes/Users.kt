package routes

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.routing
import resources.Users

fun Application.installUsers(){
	routing {
		get<Users> {  }
		post<Users> {  }
		get<Users.Id> {  }
		put<Users.Id> {  }
		delete<Users.Id> {  }
	}
}