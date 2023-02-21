package adapters.heroes

import org.ktorm.schema.*

internal object Heroes: Table<KtormHeroes>("HEROES") {
    val id = uuid("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val healthPoints = int("healthPoints").bindTo { it.healthPoints }
    val experiencePoints = int("experiencePoints").bindTo { it.experiencePoints }
    val power = int("power").bindTo { it.power }
    val armor = int("armor").bindTo { it.armor }
    val specialty = varchar("specialty").bindTo { it.specialty }
    val rarity = varchar("rarity").bindTo { it.rarity }
    val level = int("level").bindTo { it.level }
}