package ru.javawebinar.topjava.repository.jdbc.hsqldb;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository;

import static ru.javawebinar.topjava.Profiles.HSQL_DB;

@Profile(HSQL_DB)
public class HsqldbJdbcMealRepository extends JdbcMealRepository {
    public HsqldbJdbcMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }
}
