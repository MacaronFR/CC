package routes

import entities.users.UpdateUser
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ports.out.cards.CardGetter
import ports.out.combats.StartCombat
import ports.out.packs.PackOpener
import ports.out.users.*
import resources.Users
import types.CreateCombat
import types.CreateUser
import types.PackType

fun Application.installUsers(
		userCreator: UserCreator,
		userSearch: UserSearch,
		userUpdater: UserUpdater,
		packOpener: PackOpener,
		userRemoveToken: UserRemoveToken,
		userAddToken: UserAddToken,
		cardGetter: CardGetter,
		startCombat: StartCombat
){
	routing {
		get<Users> {
			val users = it.search?.let { search ->
				userSearch.searchByPseudo(search)
			} ?: userSearch.getAll()
			call.respond(users)
		}
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
		get<Users.Id.Open.Type> {
			val user = userSearch.getById(it.parent.parent.id)
			val amount = when(it.type){
				PackType.SILVER -> 1
				PackType.DIAMOND -> 2
			}
			userRemoveToken.removeToken(user, amount)
			call.respond(packOpener.open(user, it.type))
		}
		post<Users.Id.Combat.Card>{
			val user = userSearch.getById(it.parent.parent.id)
			val card = cardGetter.get(it.card)
			val body = call.receive<CreateCombat>()
			val opponent = userSearch.getById(body.user)
			val opponentCard = cardGetter.get(body.card)
			val combat = startCombat.combat(user, card, opponent, opponentCard)
			if(userAddToken.canAddToken(combat.winner)){
				userAddToken.addToken(combat.winner)
			}
			call.respond(combat)
		}
	}
}