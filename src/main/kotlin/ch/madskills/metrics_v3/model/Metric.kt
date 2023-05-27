package ch.madskills.metrics_v3.model

import java.util.UUID

data class Metric(
    val id: UUID,
    val userId: UUID,
    val metricName: String,
    val value: String,
    val createdAt: Long
)
