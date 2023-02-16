package ru.podgoretskaya.deal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.podgoretskaya.deal.client.ConveyorClient;

@SpringBootApplication
@EnableFeignClients(clients = ConveyorClient.class)
public class DealApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealApplication.class, args);
	}

}
