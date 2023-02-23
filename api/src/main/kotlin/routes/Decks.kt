package routes

import entities.card.Card
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.routing
import ports.out.deck.DeckAppender
import ports.out.deck.DeckGetter

import resources.Decks

fun Application.installDecks(
		deckAppender: DeckAppender,
		deckGetter: DeckGetter
){
	routing {
		get<Decks> { call.respond(deckGetter.getAll()) }
		get<Decks.Id> { call.respond(deckGetter.get(it.id)) }
		post<Decks.Id.Append> {
			val deck = deckGetter.get(it.parent.id)
			val card = call.receive<Card>()
			deckAppender.append(deck, card)
		}
	}
}