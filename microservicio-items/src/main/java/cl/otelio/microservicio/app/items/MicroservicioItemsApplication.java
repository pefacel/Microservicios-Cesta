package cl.otelio.microservicio.app.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RibbonClient(name = "servicio-productos")
@EnableFeignClients
public class MicroservicioItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioItemsApplication.class, args);
	}

}
