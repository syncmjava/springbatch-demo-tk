package com.example.demo.batch.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DBConfig implements EnvironmentAware {
  private Environment environment;

  @Override
  public void setEnvironment(final Environment environment) {
    this.environment = environment;
  }

  @Primary
  @Bean(name = "dataSource")
  public DataSource dataSource() throws Exception {
    Properties props = new Properties();
    props.put("driverClassName", environment.getProperty("spring.datasource.driverClassName"));
    props.put("url", environment.getProperty("spring.datasource.url"));
    props.put("username", environment.getProperty("spring.datasource.username"));
    props.put("password", environment.getProperty("spring.datasource.password"));
    return DruidDataSourceFactory.createDataSource(props);
  }

  @Bean(name = "secondDatasource")
  public DataSource secondDatasource() throws Exception {
    Properties props = new Properties();
    props.put(
        "driverClassName", environment.getProperty("spring.second-datasource.driverClassName"));
    props.put("url", environment.getProperty("spring.second-datasource.url"));
    props.put("username", environment.getProperty("spring.second-datasource.username"));
    props.put("password", environment.getProperty("spring.second-datasource.password"));
    return DruidDataSourceFactory.createDataSource(props);
  }

  @Bean(name = "thirdDatasource")
  public DataSource thirdDatasource() {
    return DataSourceBuilder.create()
        .url(environment.getProperty("spring.second-datasource.url"))
        .driverClassName(environment.getProperty("spring.second-datasource.driverClassName"))
        .username(environment.getProperty("spring.second-datasource.username"))
        .password(environment.getProperty("spring.second-datasource.password"))
        .build();
  }
}
