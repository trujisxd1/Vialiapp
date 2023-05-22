package cl.gust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cl.gust.modelos.UsuarioModel;
import cl.gust.repositorios.IUsuariosRepositorio;

@Service
@Primary
public class UsuarioServices {

	@Autowired
	
	private IUsuariosRepositorio repositorio;
	
	
	public UsuarioModel guardar(UsuarioModel entity) {
		
		
		return this.repositorio.save(entity);
		
		
	}
	
	
	public UsuarioModel buscarPorCorreo(String correo, Integer estado) {
		
		
		return this.repositorio.findByCorreoAndEstado(correo, estado);
	}
	
}
