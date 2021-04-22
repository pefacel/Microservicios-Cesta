package cl.otelio.microservicio.app.items.service;

import java.util.List;

import cl.otelio.microservicio.app.items.models.Item;

public interface IItemService {

	public List<Item> finadAll();
	
	public Item findById(Long id, Integer cantidad);
	

}
