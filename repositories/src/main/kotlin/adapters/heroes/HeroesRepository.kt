package domain.repositories

import entities.Heroes

interface HeroRepository {
    fun create(hero: Heroes): Heroes
    fun findById(id: Int): Heroes?
    fun findAll(): List<Heroes>
}