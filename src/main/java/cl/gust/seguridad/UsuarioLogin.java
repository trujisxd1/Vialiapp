package cl.gust.seguridad;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import cl.gust.modelos.AutorizarModel;
import cl.gust.modelos.UsuarioModel;
import cl.gust.service.UsuarioServices;

@Component
public class UsuarioLogin implements UserDetailsService {

	
	@Autowired
	private UsuarioServices usuarioServices;
	@Override
	
	@Transactional(readOnly = true)
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioModel usuario =this.usuarioServices.buscarPorCorreo(username, 1);
		if(usuario==null) {
			
			throw new UsernameNotFoundException("El email : " + username + "no existe");
		}
		
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(AutorizarModel role: usuario.getAutorizar()) {
			
			authorities.add(new SimpleGrantedAuthority(role.getNombre()));
		}
		
		if(authorities.isEmpty()) {
			
			
			throw new UsernameNotFoundException("error en el login " + username + "no tiene roles asigandos");
		}
		
		
		return new User(usuario.getNombre(),usuario.getPassword(),true,true,true,true, authorities);
	}

	
	
}
