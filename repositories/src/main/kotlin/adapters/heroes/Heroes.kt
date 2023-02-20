package adapters.heroes

import org.ktorm.schema.Table
import org.ktorm.schema.uuid
import org.ktorm.schema.varchar

internal object Heroes: Table<KtormHeroes>("HEROES") {
    val id = uuid("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    //toutes la dataclass
}