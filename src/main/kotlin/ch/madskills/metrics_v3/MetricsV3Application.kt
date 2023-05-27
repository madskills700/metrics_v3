package ch.madskills.metrics_v3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@EnableScheduling
@SpringBootApplication(scanBasePackages = ["ch.madskills.metrics_v3"])
class MetricsV3Application

fun main(args: Array<String>) {
    runApplication<MetricsV3Application>(*args)
}
