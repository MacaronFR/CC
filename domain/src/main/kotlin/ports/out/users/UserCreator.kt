package ports.out.users

interface UserCreator {

	val defaultToken: Int

	fun createUser(pseudo: String)
}