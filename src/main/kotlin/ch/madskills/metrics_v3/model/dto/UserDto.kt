package ch.madskills.metrics_v3.model.dto

import ch.madskills.metrics_v3.model.User
import java.util.*

data class UserDto(
    val id: UUID?,
    val name: String?,
    val created: Long?,
    val changed: Long?,
    val lastUpdateId: Long?
)

fun UserDto.toUser() = User(
    id = id!!,
    name = name!!,
    created = created!!,
    changed = changed!!,
    lastUpdateId = lastUpdateId!!
)