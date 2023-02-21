package adapters.heroes

import adapters.enumString
import entities.Rarity
import entities.Specialty
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.uuid
import org.ktorm.schema.varchar

internal object Heroes: Table<KtormHero>("HEROES") {
    val id = uuid("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val healthPoints = int("healthPoints").bindTo { it.healthPoints }
    val experiencePoints = int("experiencePoints").bindTo { it.experiencePoints }
    val power = int("power").bindTo { it.power }
    val armor = int("armor").bindTo { it.armor }
    val specialty = enumString<Specialty>("specialty").bindTo { it.specialty }
    val rarity = enumString<Rarity>("rarity").bindTo { it.rarity }
    val level = int("level").bindTo { it.level }
}