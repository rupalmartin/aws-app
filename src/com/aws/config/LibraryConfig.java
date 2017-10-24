package com.aws.config;

import com.aws.dao.FileInfoDao;
import com.aws.dao.UserDao;
import com.aws.service.AdminService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class LibraryConfig {

    @Bean
    public AdminService adminService() {
        return new AdminService();
    }

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public FileInfoDao fileInfoDao() {
        return new FileInfoDao();
    }

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://aws-app-281-db.ckg0faguue9u.us-east-1.rds.amazonaws.com:3306/aws-app-281-db");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("rupaldb");
        ds.setPassword("rmartin31");

        return ds;
    }

    @Bean
    public JdbcTemplate template() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());

        return template;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}