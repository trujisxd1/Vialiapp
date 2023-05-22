package cl.gust.controller;

import java.security.Principal;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.gust.modelos.AutorizarModel;
import cl.gust.modelos.UsuarioModel;
import cl.gust.service.AutorizarService;
import cl.gust.service.UsuarioServices;

@Controller
@RequestMapping ("/acceso")
public class AccesoController {

	@GetMapping("/login")
	
	public String login (@RequestParam(value="error" , required = false) String error 
			, @RequestParam(value="logout", required = false)String logout, RedirectAttributes flash,Principal principal ) {
		
		
		if(principal != null) {
			
			flash.addFlashAttribute("clase", "success");

			flash.addFlashAttribute("mensaje", "Ya ha iniciado session anteriormente");
			
			return "redirect:/";
				
		}
		
		if(error!=null) {
		
			flash.addFlashAttribute("clase", "danger");

			flash.addFlashAttribute("mensaje", "los datos no son correctos favor de volver a intentarlo");
			
			return "redirect:/acceso/login";
			
		}
		
		if(logout!=null) {
			
			flash.addFlashAttribute("clase", "success");

			flash.addFlashAttribute("mensaje", "ha cerrado sesion existosamente");
			
			return "redirect:/acceso/login";
		}
		
		return "acceso/login";
	}
	
	
	@GetMapping("/registro")
	
	public String registro(Model model) {
		
		model.addAttribute("usuario", new UsuarioModel());
		
		return "acceso/addUser";
	}
	
	
	@PostMapping("/registro")
	
	public String registro_post(@Valid UsuarioModel usuario,BindingResult result, RedirectAttributes flash, Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("usuario", usuario);
			return "acceso/registro";
		}
		
		
		UsuarioModel guardar=this.usuarioServices.guardar(new UsuarioModel(usuario.getNombre(), usuario.getCorreo(),
				usuario.getTelefono(), this.bCryptPasswordEncoder.encode(usuario.getPassword()),1,null));
		
		this.autorizarService.guardar(new AutorizarModel("ROLE_ADMIN", guardar));
		flash.addFlashAttribute("success", "registro exitoso");
		
		return "redirect:/acceso/registro";
	}
	
	
	
	@Autowired
	private UsuarioServices usuarioServices;
	
	@Autowired
	private AutorizarService autorizarService;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
}

