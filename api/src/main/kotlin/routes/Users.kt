package routes

import entities.users.UpdateUser
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.routing
import ports.out.users.UserCreator
import ports.out.users.UserSearch
import ports.out.users.UserUpdater
import resources.Users
import types.CreateUser

fun Application.installUsers(
		userCreator: UserCreator,
		userSearch: UserSearch,
		userUpdater: UserUpdater
){
	routing {
		get<Users> { call.respond(userSearch.getAll()) }
		post<Users> {
			val data = call.receive<CreateUser>()
			call.respond(userCreator.createUser(data.pseudo))
		}
		get<Users.Id> { call.respond(userSearch.getById(it.id)) }
		put<Users.Id> {
			val data = call.receive<UpdateUser>()
			call.respond(userUpdater.update(it.id, data))
		}
		delete<Users.Id> {  }
	}
}