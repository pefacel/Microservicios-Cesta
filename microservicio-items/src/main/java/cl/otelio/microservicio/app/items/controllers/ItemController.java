package cl.otelio.microservicio.app.items.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cl.otelio.microservicio.app.items.models.Item;
import cl.otelio.microservicio.app.items.service.ItemServiceFeign;

@RestController
public class ItemController {

	@Autowired
	private ItemServiceFeign itemService;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.finadAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
}
