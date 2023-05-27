package ch.madskills.metrics_v3.controller.metrics

import ch.madskills.metrics_v3.model.dto.MetricDto
import ch.madskills.metrics_v3.service.metrics.MetricsService
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class MetricsControllerImpl(
    private val metricsService: MetricsService
): MetricsController {

    override fun getAll(): List<MetricDto> {
        TODO("Not yet implemented")
    }

    override fun create(metric: MetricDto): UUID {
        TODO("Not yet implemented")
    }
}