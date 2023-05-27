package ch.madskills.metrics_v3.service.user

import ch.madskills.metrics_v3.model.User
import java.util.UUID

interface UserService {

    fun readAll(): List<User>

    fun create(user: User): UUID
}