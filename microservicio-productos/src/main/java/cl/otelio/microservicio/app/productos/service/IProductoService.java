package cl.otelio.microservicio.app.productos.service;

import java.util.List;

import cl.otelio.microservicio.app.productos.models.entity.Producto;

public interface IProductoService  {

	public List<Producto> findAll();
	public Producto findById(Long id);
}
