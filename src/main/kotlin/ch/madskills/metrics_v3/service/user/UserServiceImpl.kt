package ch.madskills.metrics_v3.service.user

import ch.madskills.metrics_v3.model.User
import ch.madskills.metrics_v3.repository.user.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    override fun readAll(): List<User> {
        return userRepository.getAll()
    }

    override fun create(user: User): UUID {
        return userRepository.create(user)
    }
}