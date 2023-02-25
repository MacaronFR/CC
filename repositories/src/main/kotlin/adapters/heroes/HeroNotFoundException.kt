package adapters.heroes

import ports.NotFoundException

class HeroNotFoundException: NotFoundException("heroes not found")