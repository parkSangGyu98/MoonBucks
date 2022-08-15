package com.varxyz.javaCafe.data;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.javaCafe.dao.CategoryDao;
import com.varxyz.javaCafe.dao.MenuDao;


@Configuration 
public class DataSourceConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/javaCafe?serverTimezone=Asia/Seoul");
		ds.setUsername("javaCafe");
		ds.setPassword("javaCafe");
		ds.setInitialSize(2); 
		ds.setMaxActive(10);  
		ds.setMaxIdle(10); 	  
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public CategoryDao categoryDao() {
		return new CategoryDao(dataSource());
	}
	
	@Bean
	public MenuDao menuDao() {
		return new MenuDao(dataSource());
	}
	
	
}