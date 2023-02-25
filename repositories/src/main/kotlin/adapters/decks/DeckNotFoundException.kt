package adapters.decks

import ports.NotFoundException

class DeckNotFoundException: NotFoundException("Deck not found")