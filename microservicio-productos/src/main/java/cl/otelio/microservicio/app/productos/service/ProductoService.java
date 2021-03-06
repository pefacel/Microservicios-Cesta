package cl.otelio.microservicio.app.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.otelio.microservicio.app.productos.models.entity.repository.IProductoDao;
import cl.otelio.microservicio.commons.models.entity.Producto;

@Service
public class ProductoService implements IProductoService {

	@Autowired
	private IProductoDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return  dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return dao.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		dao.deleteById(id);		
	}

}
