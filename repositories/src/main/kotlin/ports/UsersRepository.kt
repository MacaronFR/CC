package ports

import entities.Users
import java.util.*

interface UsersRepository: Repository<UsersRepository, Users, UUID>