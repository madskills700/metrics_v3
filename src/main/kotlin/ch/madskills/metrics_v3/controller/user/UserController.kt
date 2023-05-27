package ch.madskills.metrics_v3.controller.user

import ch.madskills.metrics_v3.model.dto.UserDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@Tag(name = "Сервис для работы с метриками")
@RequestMapping(path = ["/users"])
interface UserController {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить пользователей")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): List<UserDto>

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Создать пользователя")
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(userDto: UserDto): UUID

}