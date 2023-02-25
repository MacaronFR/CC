package adapters.cards

import ports.NotFoundException

class CardNotFoundException: NotFoundException("Card not found")