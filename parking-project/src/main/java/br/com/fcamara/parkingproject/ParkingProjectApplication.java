package br.com.fcamara.parkingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ParkingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingProjectApplication.class, args);
	}

}
