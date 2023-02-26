package services.users

import entities.users.User
import ports.UsersRepository
import ports.out.exception.NoTokenLeftException
import ports.out.users.UserRemoveToken

class UserRemoveTokenService(private val userRepo: UsersRepository): UserRemoveToken {
	override fun removeToken(user: User, amount: Int) {
		if(user.token >= amount){
			userRepo.removeToken(user.id, amount)
		}else{
			throw NoTokenLeftException()
		}
	}
}