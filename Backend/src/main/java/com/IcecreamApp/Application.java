package com.IcecreamApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
   public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
   
//   @Bean
//   public CommandLineRunner InitData {DogRepository repository) {
//
//	   	return args -> {
//	   		repository.save(new Dog((long) 1, "ChiHuahua", 99.0));
//	   		repository.save(new Dog((long) 2, "NgaoDa", 99.0));
//	   		repository.save(new Dog((long) 3, "NgaoCo", 99.0));
//	   	}
//	}
}