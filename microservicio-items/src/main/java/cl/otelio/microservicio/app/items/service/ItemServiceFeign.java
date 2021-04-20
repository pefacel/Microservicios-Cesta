package cl.otelio.microservicio.app.items.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.otelio.microservicio.app.items.feign.ProductoClienteRest;
import cl.otelio.microservicio.app.items.models.Item;

@Service
public class ItemServiceFeign implements IItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> finadAll() {
		return clienteFeign.listar().stream().map(p-> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.ver(id), cantidad);
	}

}
