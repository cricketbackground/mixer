package com.async.mixer.mixer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MixerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MixerApplication.class, args);
	}

}
