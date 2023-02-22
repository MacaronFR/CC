import adapters.cards.KtormCardsRepository
import adapters.decks.KtormDecksRepository
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.ktorm.database.Database
import plugins.contentNegotiation
import plugins.resources
import routes.installDecks
import services.decks.DeckAppenderService
import services.decks.DeckGetterService

fun main(args: Array<String>){
	println("Starting …")
	EngineMain.main(args)
	println("Stopping")
}

fun Application.module(){
	val db = Database.connect("jdbc:mariadb://localhost:3306/CC", user = "root", password = "root")
	val cardRepo = KtormCardsRepository(db)
	val deckRepo = KtormDecksRepository(db, cardRepo)
	val deckAppender = DeckAppenderService(deckRepo)
	val deckGetter = DeckGetterService(deckRepo)
	contentNegotiation()
	resources()
	installDecks(deckAppender, deckGetter)
}