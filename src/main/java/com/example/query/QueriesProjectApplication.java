package com.example.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.Jpa.QueryJpa;
import com.example.model.ClientModel;
import com.example.model.EmployeeModel;
import com.example.model.QueryModel;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackageClasses=QueryJpa.class)
@EntityScan(basePackageClasses= {QueryModel.class,EmployeeModel.class,ClientModel.class})
@ComponentScan(basePackages = {"com.example"})
public class QueriesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueriesProjectApplication.class, args);
	}
	
	
	
	
	

}
