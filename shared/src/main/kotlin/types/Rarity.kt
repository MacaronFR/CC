package types

import serializer.enums.TEnum
import serializer.enums.string.AbstractEnumStringSerializer

object RaritySerializer: AbstractEnumStringSerializer<Rarity>(Rarity::class)

enum class Rarity(override val value: String): TEnum<String> {
    COMMON("common"),
    RARE("rare"),
    LEGENDARY("legendary");

    override fun getValue(value: String): TEnum<String> = values().find { it.value == value }!!
}