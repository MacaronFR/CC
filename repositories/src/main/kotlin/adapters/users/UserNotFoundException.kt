package adapters.users

import ports.NotFoundException

class UserNotFoundException: NotFoundException("User not found")