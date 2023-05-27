package ch.madskills.metrics_v3.controller.user

import ch.madskills.metrics_v3.model.dto.UserDto
import ch.madskills.metrics_v3.model.dto.toUser
import ch.madskills.metrics_v3.model.toDto
import ch.madskills.metrics_v3.service.user.UserService
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserControllerImpl(
    private val userService: UserService
): UserController {

    override fun getAll(): List<UserDto> {
        return userService.readAll().map { it.toDto() }
    }

    override fun create(userDto: UserDto): UUID {
        return userService.create(userDto.toUser())
    }

}