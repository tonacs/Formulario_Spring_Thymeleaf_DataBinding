package com.tona.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tona.springboot.form.app.editors.NombreMayusculaEditor;
import com.tona.springboot.form.app.editors.PaisPropertyEditor;
import com.tona.springboot.form.app.editors.RolesEditor;
import com.tona.springboot.form.app.models.domain.Pais;
import com.tona.springboot.form.app.models.domain.Role;
import com.tona.springboot.form.app.models.domain.Usuario;
import com.tona.springboot.form.app.services.PaisService;
import com.tona.springboot.form.app.services.RoleService;
import com.tona.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private PaisPropertyEditor paisPropertyEditor;
	
	@Autowired
	private RolesEditor rolesEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		dateformat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateformat, true));
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisPropertyEditor);
		binder.registerCustomEditor(Role.class, "roles", rolesEditor);
	}
	
	
	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre", "Mujer");
	}
	
	
	@ModelAttribute("listaRoles")
	public List<Role> listarRoles(){
		return this.roleService.listar();
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();

	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String>  roles=new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
	
		return roles;
	}
	

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("Espa??a", "M??xico", "Chile", "Per??", "Colombia", "Venezuela");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<>();
		paises.put("ES", "Espa??a");
		paises.put("MX", "M??xico");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Per??");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");
		return paises;
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("John");
		usuario.setApellido("Doe");
		usuario.setIdentificador("12.456.789-K");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Alg??n valor secreto");	
		usuario.setPais(new Pais(2,"MX","M??xico"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER"),new Role(3, "Moderador", "ROLE_MODERATOR")));
		usuario.setEmail("t@t.com");
		usuario.setUsername("chilaki");
		usuario.setCuenta(666);
		model.addAttribute("titulo", "Formulario de usuarios");
		model.addAttribute("usuario", usuario);
		
		return "form";
	}

//public String procesar(@Valid @ModelAttribute("user") Usuario usuario Indica el nombre por el ccual pasa 
//	a la vista en caso de que la validaci??n falle
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
//		validador.validate(usuario, result);

		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Resultado form");
//			Map<String,String> errores=new HashMap<>();
//			result.getFieldErrors().forEach(err->{
//				errores.put(err.getField(), "El campo "
//						.concat(err.getField()
//						.concat(" ")
//						.concat(err.getDefaultMessage())));
//			});
//			model.addAttribute("error",errores);
			return "form";
		}
		

		return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required = false) Usuario usuario, Model model, SessionStatus status) {
		if(usuario==null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado form");
		status.setComplete();
		return "resultado";
	}
	
	
	
}
