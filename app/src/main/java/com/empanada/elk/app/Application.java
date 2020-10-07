package com.empanada.elk.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private final static String APP_VAR = "SPRING_APP_NAME";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	 private static String getEnvOrSystemVariable(String name){
		String env = System.getenv(name);
		if (env != null )
			return "Environment " + env;
		return "System " + System.getProperty(name);
	 }
}
