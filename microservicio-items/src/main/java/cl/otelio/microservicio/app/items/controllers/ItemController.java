package cl.otelio.microservicio.app.items.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cl.otelio.microservicio.app.items.models.Item;
import cl.otelio.microservicio.app.items.service.ItemServiceFeign;
import cl.otelio.microservicio.commons.models.entity.Producto;

@RestController
@RefreshScope
public class ItemController {

	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ItemServiceFeign itemService;
	
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@Value("${server.port}")
	private String port;

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
	
	@GetMapping("/config")
	public ResponseEntity<?> obtenerConfig(){
		log.info(texto);
		
		Map<String,String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("port", port);
		
		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}

		return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
	}

}
