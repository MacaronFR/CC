package routes

import entities.heroes.Hero
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.routing
import io.ktor.server.resources.*
import io.ktor.server.response.*
import ports.out.heroes.HeroCreator
import ports.out.heroes.HeroSearcher
import resources.Heroes
import types.CreateHero

fun Application.installHeroes(
		heroSearcher: HeroSearcher,
		heroCreator: HeroCreator
){
	routing {
		get<Heroes> {
			call.respond(heroSearcher.getAll())
		}
		post<Heroes> {
			val data = call.receive<CreateHero>()
			val hero = Hero.Factory(data.name, data.speciality, data.rarity)
			call.respond(heroCreator.create(hero))
		}
		get<Heroes.Id> {
			call.respond(heroSearcher.get(it.id))
		}
	}
}