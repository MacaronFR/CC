package routes

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.routing
import ports.out.DeckAppender

import resources.Decks

fun Application.installDecks(deckAppender: DeckAppender){
	routing {
		get<Decks> {  }
		post<Decks> {  }
		get<Decks> {  }
		put<Decks.Id> {  }
		delete<Decks.Id> {  }
	}
}