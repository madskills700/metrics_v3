package ch.madskills.metrics_v3.repository.user

import ch.madskills.metrics_v3.model.User
import java.util.UUID

interface UserRepository {

    fun create(user: User) : UUID

    fun getAll() : List<User>

}