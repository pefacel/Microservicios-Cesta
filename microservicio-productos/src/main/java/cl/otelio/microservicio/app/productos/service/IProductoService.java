package cl.otelio.microservicio.app.productos.service;

import java.util.List;

import cl.otelio.microservicio.commons.models.entity.Producto;

public interface IProductoService  {

	public List<Producto> findAll();
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public void deleteById(Long id);
}
