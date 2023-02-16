package ports

interface Repository<R: Repository<R, E, I>, E, I> {
    fun read(id: I): E

    fun readAll(): List<E>

    fun create(value: E): E

    fun update(id: I): E

    fun delete(id: I): E
}