package cl.otelio.microservicio.app.items.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cl.otelio.microservicio.app.items.models.Item;
import cl.otelio.microservicio.app.items.models.Producto;
import cl.otelio.microservicio.app.items.service.ItemServiceFeign;

@RestController
public class ItemController {

	@Autowired
	private ItemServiceFeign itemService;

	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.finadAll();
	}

	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}

	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setPrecio(0.0);
		producto.setNombre("Error al buscar el producto solicitado. Estamos trabajando para usted");
		item.setProducto(producto);
		return item;
	}

}
