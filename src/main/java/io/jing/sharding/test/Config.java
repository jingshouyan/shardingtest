package io.jing.sharding.test;

import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.core.constant.ShardingPropertiesConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
public class Config {


    private String url ="jdbc:mysql://127.0.0.1:3306/SHARD_TEST?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private String username="jing";
    private String password="Jing1234!@#$";
    private String driver="com.mysql.jdbc.Driver";


    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


    @Bean
    public DataSource dataSource() throws Exception{
        Map<String,DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put("default",initDataSource());

        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();

        shardingRuleConfiguration.setDefaultDataSourceName("default");
        Properties properties = new Properties();
        properties.setProperty(ShardingPropertiesConstant.SQL_SHOW.getKey(),"true");
        Map<String, Object> configMap = Maps.newHashMap();
        DataSource dataSource =  ShardingDataSourceFactory.createDataSource(dataSourceMap,shardingRuleConfiguration,configMap,properties);

        return dataSource;
    }

    private DataSource initDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setDriverClassName(driver);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
