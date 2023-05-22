package cl.gust.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.gust.modelos.UsuarioModel;

public interface IUsuariosRepositorio extends JpaRepository<UsuarioModel, Integer> {

	public UsuarioModel findByCorreoAndEstado(String correo, Integer estado);
	
}
