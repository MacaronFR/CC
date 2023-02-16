package fr.imacaron.CC.adapter.`in`

interface Repository<R: Repository<R, I>, I> {
    fun read(id: I): R

    fun readAll(): List<R>

    fun create(value: R): R

    fun update(id: I): R

    fun delete(id: I): R
}