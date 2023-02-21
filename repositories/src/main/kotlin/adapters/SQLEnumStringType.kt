package adapters

import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.SqlType
import serializer.enums.TEnum
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

class SQLEnumStringType<C: TEnum<String>>(private val enumClass: Class<C>): SqlType<C>(Types.VARCHAR, "varchar") {

	private val method = enumClass.getDeclaredMethod("getValue", String::class.java).also { it.isAccessible = true }

	override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: C) {
		ps.setString(index, parameter.value)
	}

	override fun doGetResult(rs: ResultSet, index: Int): C? {
		return rs.getString(index).let { enumClass.cast(method(enumClass.enumConstants.first(), it)) }
	}

}

inline fun <reified C: TEnum<String>> BaseTable<*>.enumString(name: String): Column<C> {
	return registerColumn(name, SQLEnumStringType(C::class.java))
}