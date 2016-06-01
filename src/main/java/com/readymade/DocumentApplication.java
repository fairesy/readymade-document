package com.readymade;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication(exclude={org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class})
public class DocumentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentApplication.class, args);
	}
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager txManager(){
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		return manager;
	}

}
