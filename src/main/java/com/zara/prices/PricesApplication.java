package com.zara.prices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class PricesApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(PricesApplication.class);
		ConfigurableApplicationContext appConfig = app.run(args);
		Environment env = appConfig.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		log.info(
				" Swagger 2 : \t\thttp://{}:{}/swagger-ui/index.html\n\t",
				ip,
				env.getProperty("server.port"));
	}

}
