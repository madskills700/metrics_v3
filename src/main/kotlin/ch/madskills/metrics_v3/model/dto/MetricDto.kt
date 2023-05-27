package ch.madskills.metrics_v3.model.dto

import ch.madskills.metrics_v3.model.Metric
import java.util.UUID

data class MetricDto(
    val id: UUID?,
    val userId: UUID?,
    val metricName: String?,
    val value: String?,
    val createdAt: Long?
)

fun MetricDto.toMetric() = Metric(
    id = id!!,
    userId = userId!!,
    metricName = metricName!!,
    value = value!!,
    createdAt = createdAt!!
)
