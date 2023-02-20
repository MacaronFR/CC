package ports

import entities.Heroes
import java.util.*

interface HeroesRepository: Repository<HeroesRepository, Heroes, UUID>