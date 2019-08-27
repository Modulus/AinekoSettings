package com.aineko.settings;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration()
public class SettingsApplication {



	public static void main(String[] args) {
		System.out.println(System.getenv());
		SpringApplication.run(SettingsApplication.class, args);


	}

}


