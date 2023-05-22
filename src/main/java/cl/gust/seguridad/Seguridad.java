package cl.gust.seguridad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Seguridad {

	
	@Autowired
	
	private LoginPerzonalizado loginPerzonalizado;
	
	@Bean
	
	public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		
		return  authenticationConfiguration.getAuthenticationManager();
		
	}
	
	
	@Bean
	
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
		
	}
	@Bean
	
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
		
		.antMatchers("/acceso","/uploads").permitAll().antMatchers("/").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
		.anyRequest().authenticated().and().formLogin().successHandler(loginPerzonalizado).loginPage("/acceso/login").permitAll()
		.and().logout().permitAll();
		
		return http.build();
		
		
	
		
	}
	
	
	@Bean
	
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().antMatchers("/img/**", "/Styles/**","/static/**");
	}

	
	
}
