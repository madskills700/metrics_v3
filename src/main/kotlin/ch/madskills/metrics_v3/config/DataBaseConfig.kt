package ch.madskills.metrics_v3.config

import liquibase.Liquibase
import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import ru.yandex.clickhouse.ClickHouseDataSource
import java.util.Properties
import javax.sql.DataSource

@Configuration
class DataBaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    fun postgresDataSourceProperties() : DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.clickhouse")
    fun clickhouseDataSourceProperties() : DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.postgres.hikari")
    fun postgresDataSource() : DataSource {
        return postgresDataSourceProperties()
            .initializeDataSourceBuilder()
            .build()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.clickhouse.hikari")
    fun clickhouseDataSource() : DataSource {
        val dsp = clickhouseDataSourceProperties()
        val url = dsp.url
        val props = Properties()
        props["user"] = dsp.username
        props["password"] = dsp.password
        return ClickHouseDataSource(url, props)
    }

    @Bean
    fun postgresJdbcTemplate(
        @Qualifier("postgresDataSource")
        dataSource: DataSource
    ): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    fun clickhouseJdbcTemplate(
        @Qualifier("clickhouseDataSource")
        dataSource: DataSource
    ): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean("liquibase")
    fun postgresLiquibase() : SpringLiquibase {
        return springLiquibase(postgresDataSource(), liquibasePostgresProperties())
    }

    @Bean("liquibase-clickhouse")
    fun clickhouseLiquibase() : SpringLiquibase {
        return springLiquibase(clickhouseDataSource(), liquibaseClickHouseProperties())
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgres.liquibase")
    fun liquibasePostgresProperties(): LiquibaseProperties {
        return LiquibaseProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.clickhouse.liquibase")
    fun liquibaseClickHouseProperties(): LiquibaseProperties {
        val props = LiquibaseProperties()
        // todo костыль
        props.defaultSchema = "metrics"
        return props
    }

    private fun springLiquibase(
        dataSource: DataSource,
        properties: LiquibaseProperties
    ): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.changeLog = properties.changeLog
        liquibase.contexts = properties.contexts
        liquibase.defaultSchema = properties.defaultSchema
        liquibase.isDropFirst = properties.isDropFirst
        liquibase.setShouldRun(properties.isEnabled)
        liquibase.labels = properties.labels
        liquibase.setChangeLogParameters(properties.parameters)
        liquibase.setRollbackFile(properties.rollbackFile)
        return liquibase
    }

}