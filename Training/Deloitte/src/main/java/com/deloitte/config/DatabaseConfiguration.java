package com.deloitte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import javax.sql.DataSource;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(HSQL)
                .setName("deloitte")
                .addScript("classpath:schema-hsqldb.sql")
                .addScript("classpath:data-hsqldb.sql")
                .build();
    }
}