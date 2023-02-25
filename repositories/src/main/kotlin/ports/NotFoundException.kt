package ports

abstract class NotFoundException(override val message: String): Exception(message)