package routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import ports.out.combats.CombatSearcher
import resources.Combats

fun Application.installCombats(
		combatSearcher: CombatSearcher
) {
	routing {
		get<Combats> { call.respond(combatSearcher.getAll()) }
		get<Combats.User.Id> { call.respond(combatSearcher.searchCombatByUser(it.id)) }
		get<Combats.Hero.Id> { call.respond(combatSearcher.searchCombatByHero(it.id)) }
	}
}