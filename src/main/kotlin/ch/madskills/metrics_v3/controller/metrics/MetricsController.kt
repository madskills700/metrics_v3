package ch.madskills.metrics_v3.controller.metrics

import ch.madskills.metrics_v3.model.dto.MetricDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@RequestMapping(path = ["/metrics"])
@Tag(name = "Сервис для работы с метриками")
interface MetricsController {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить метрики")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): List<MetricDto>

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Создать метрики")
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(metric: MetricDto): UUID

}