package ch.madskills.metrics_v3.model

import ch.madskills.metrics_v3.model.dto.UserDto
import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val created: Long,
    val changed: Long,
    val lastUpdateId: Long
)

fun User.toDto() = UserDto(
    id = id,
    name = name,
    created = created,
    changed = changed,
    lastUpdateId = lastUpdateId
)