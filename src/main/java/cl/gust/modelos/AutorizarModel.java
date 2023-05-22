package cl.gust.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="autorizar")
public class AutorizarModel {

	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	@OneToOne
	@JoinColumn(name="usuarios_id")
	
	
	
	
	
	private UsuarioModel usurioId;
	public AutorizarModel(String nombre, UsuarioModel usurioId) {
		super();
		this.nombre = nombre;
		this.usurioId = usurioId;
	}
	
	
	public AutorizarModel() {
		super();
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public UsuarioModel getUsurioId() {
		return usurioId;
	}
	public void setUsurioId(UsuarioModel usurioId) {
		this.usurioId = usurioId;
	}
	
	
	
	
	
	
}
