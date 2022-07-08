package com.icecream.product;

import com.icecream.product.migrate.ProductServiceMigrate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters=@ComponentScan.Filter(SpringBootApplication.class))
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
