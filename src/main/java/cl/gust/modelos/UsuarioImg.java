package cl.gust.modelos;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "upload_files")
public class UsuarioImg {

	@Id
	private String id;
	private String nombre;
	private String apellido;
	private String email;
	private String foto;
	private String lat;
	private String lng;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public UsuarioImg(String id, String nombre, String apellido, String email, String foto, String lat, String lng) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.foto = foto;
		this.lat = lat;
		this.lng = lng;
	}
	public UsuarioImg() {
		super();
	}
	
	
	
	
}
