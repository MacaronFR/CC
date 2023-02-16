package ports

import entities.Deck
import java.util.*

interface DecksRepository: Repository<DecksRepository, Deck , UUID>