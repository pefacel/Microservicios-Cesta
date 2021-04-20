package cl.otelio.microservicio.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cl.otelio.microservicio.app.productos.models.entity.Producto;

@SpringBootApplication
public class MicroservicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioProductosApplication.class, args);
		
		
	}

}
