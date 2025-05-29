package com.example.mykhailoshevchenko05.components;

import com.example.mykhailoshevchenko05.CurrentDatasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BaseConfig {

    @Bean
    public DataSource datasource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DataSource datasource1 = datasource1();
        DataSource datasource2 = datasource2();
        targetDataSources.put(CurrentDatasource.DATASOURCE_1, datasource1);
        targetDataSources.put(CurrentDatasource.DATASOURCE_2, datasource2);

        DataSourceRouting datasourceRouting = new DataSourceRouting();
        datasourceRouting.setTargetDataSources(targetDataSources);
        datasourceRouting.setDefaultTargetDataSource(datasource1);
        return datasourceRouting;
    }

    private DataSource datasource1() {
        EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
        return dbBuilder.setType(EmbeddedDatabaseType.H2)
                .setName("datasource1")
                .addScript("init.sql")
                .build();
    }

    private DataSource datasource2() {
        EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
        return dbBuilder.setType(EmbeddedDatabaseType.H2)
                .setName("datasource2")
                .addScript("init.sql")
                .build();
    }
}
