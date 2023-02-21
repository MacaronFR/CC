package serializer.enums

interface TEnum<T> {

	val value: T

	fun getValue(value: T): TEnum<T>

}