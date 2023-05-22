package cl.gust.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.gust.modelos.AutorizarModel;

public interface IAutorizarRepositorio extends JpaRepository<AutorizarModel, Integer>{

}
