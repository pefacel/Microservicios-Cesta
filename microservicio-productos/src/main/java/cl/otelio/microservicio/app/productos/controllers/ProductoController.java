package cl.otelio.microservicio.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cl.otelio.microservicio.app.productos.models.entity.Producto;
import cl.otelio.microservicio.app.productos.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(producto ->{
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto ver(@PathVariable Long id){
		Producto producto = productoService.findById(id);
		producto.setPort(port);
		return producto;
	}
	
	
}
