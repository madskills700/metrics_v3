package ch.madskills.metrics_v3.repository.user.db

import ch.madskills.metrics_v3.model.User
import ch.madskills.metrics_v3.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.DependsOn
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*

@Repository
@DependsOn("postgresJdbcTemplate", "liquibase")
class JdbcUserRepository(
    @Qualifier("postgresJdbcTemplate")
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
): UserRepository {

    override fun create(user: User): UUID {
        namedParameterJdbcTemplate.update(
            """
                insert into users (id, name, created, changed, last_update_id) 
                values (:id, :name, :created, :changed, :last_update_id);
            """.trimIndent(),
            MapSqlParameterSource()
                .addValue("id", user.id)
                .addValue("name", user.name)
                .addValue("created", user.created)
                .addValue("changed", user.changed)
                .addValue("last_update_id", user.lastUpdateId)
        )
        return user.id
    }

    override fun getAll(): List<User> {
        return namedParameterJdbcTemplate.query(
            """
                    select * from users;
            """.trimIndent(),
            ResultSetExtractor { rs ->
                val result = mutableListOf<User>()
                while (rs.next()) {
                    result.add(rs.toUser())
                }
                result
            }
        )!!
    }

    private fun ResultSet.toUser() : User = User(
        id = getObject("id", UUID::class.java),
        name = getString("name"),
        created = getLong("created"),
        changed = getLong("changed"),
        lastUpdateId = getLong("last_update_id")
    )
}