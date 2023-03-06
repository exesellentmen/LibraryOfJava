package com.paul.startclass;

import com.paul.startclass.models.BIblock;
import com.paul.startclass.repository.BlockRepository;
import com.paul.startclass.services.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StartclassApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(StartclassApplication.class, args);
	}

}
