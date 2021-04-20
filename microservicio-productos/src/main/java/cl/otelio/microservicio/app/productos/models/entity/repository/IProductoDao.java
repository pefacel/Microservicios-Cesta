package cl.otelio.microservicio.app.productos.models.entity.repository;

import org.springframework.data.repository.CrudRepository;

import cl.otelio.microservicio.app.productos.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

}
