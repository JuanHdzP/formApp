package com.juan.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

//import java.util.HashMap;
//import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.juan.springboot.form.app.editors.MayusEditor;
import com.juan.springboot.form.app.editors.PaisPropertyEditor;
import com.juan.springboot.form.app.editors.RolePropertyEditor;
import com.juan.springboot.form.app.models.domain.Pais;
import com.juan.springboot.form.app.models.domain.Role;
import com.juan.springboot.form.app.models.domain.Usuario;
import com.juan.springboot.form.app.services.PaisService;
import com.juan.springboot.form.app.services.RoleService;
import com.juan.springboot.form.app.validation.UsuarioValidacion;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidacion validador;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private RolePropertyEditor roleEditor;

	// Cuando se inicializa spring añadimos nuestro validador al binder de esta
	// forma ya no tenemos que poner "validador.validate(usuario, result);" cuando
	// queramos usar esa validacion
	// Dar formato a los Date desde initBinder (tambien se puede con anotacion)
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Para que sea estricto con el formato establecido
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true para que acepte null, y
																							// lance el mensaje de la
																							// anotacion @NotNull
		// Para campos especificos
		// binder.registerCustomEditor(Date.class, "fechaNacimiento", new
		// CustomDateEditor(dateFormat, false));

		// binder.registerCustomEditor de un editor creado por nosotros (MayusEditor)
		// aplicado al campo nombre
		binder.registerCustomEditor(String.class, "nombre", new MayusEditor());

		// binder para traer objeto completo
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);

	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Juan");
		usuario.setApellido("Hernandez");
		usuario.setId("JU.HE.P.1305-jhp");
		usuario.setCuenta(1313);
		usuario.setEmail("juan@juan.ju");
		usuario.setUsername("Kazu");
		usuario.setValorSecreto("Llave secreta");
		usuario.setPais(new Pais(2, "MX", "Mexico"));
		usuario.setRoles(
				Arrays.asList(new Role(1, "Administrador", "ROLE_ADMIN"), new Role(2, "Usuario", "ROLE_USER")));
		usuario.setTerminos(true);
		model.addAttribute("titulo", "Formulario Usuario");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	// se puede usar name, value o nada y definir despues de RequestParam
	@PostMapping("/formInicial")
	public String procesarForm(Model model, @RequestParam(name = "username") String username,
			@RequestParam(value = "email") String email, @RequestParam String password) {

		model.addAttribute("titulo", "Resultado del form");
		model.addAttribute("username", username);
		model.addAttribute("email", email);
		model.addAttribute("password", password);

		return "resultado";
	}

	// Post usando instancia de clase
	@PostMapping("/formClase")
	public String procesarFormClase(Model model, @RequestParam(name = "username") String username,
			@RequestParam(value = "email") String email, @RequestParam String password) {

		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setEmail(email);
		usuario.setPassword(password);

		model.addAttribute("titulo", "Resultado del form");
		model.addAttribute("usuario", usuario);

		return "resultadoClase";
	}

	// Mapedo automatico cuando coinciden atributos con parametros recibidos debe
	// haber metodo getter y setter
	// Implementacion validacion basica
	@PostMapping("/form")
	public String procesarFormClaseMapeo(@Valid Usuario usuario, BindingResult result, Model model) {
		// validador.validate(usuario, result);

		if (result.hasErrors()) {

			// Manejo de errores manual
			// Map<String, String> errores = new HashMap<>();
			// result.getFieldErrors().forEach(err -> {
			// errores.put(err.getField(),
			// "El campo ".concat(err.getField().concat("
			// ").concat(err.getDefaultMessage())));
			// });
			// model.addAttribute("error", errores);
			model.addAttribute("titulo", "Resultado del form");
			return "form";
		}
		return "redirect:/ver";
	}

	@GetMapping("/ver")
	public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model,
			SessionStatus status) {
		if(usuario == null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado del form");
		status.setComplete();
		return "resultadoClase";
	}

	@ModelAttribute("paisesList")
	public List<String> paisesList() {
		return Arrays.asList("España", "Mexico", "Chile", "Argentina", "Colombia");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("MX", "Mexico");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("CO", "Colombia");

		return paises;
	}

	// Sin servicio
	// @ModelAttribute("paisesListClase")
	// public List<Pais> paisesListClase() {
	// return Arrays.asList(
	// new Pais(1, "ES", "España"),
	// new Pais(2, "MX", "Mexico"),
	// new Pais(3, "CL", "Chile"),
	// new Pais(4, "AR", "Argentina"),
	// new Pais(5, "CO", "Colombia"));
	// }

	// Con servicio
	@ModelAttribute("paisesListClase")
	public List<Pais> paisesListClase() {
		return paisService.listar();
	}

	@ModelAttribute("rolesList")
	public List<String> rolesList() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}

	@ModelAttribute("rolesMap")
	public Map<String, String> rolesMap() {
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
		return roles;
	}

	// Con servicio
	@ModelAttribute("rolesListClase")
	public List<Role> rolesListClase() {
		return this.roleService.listar();
	}

	@ModelAttribute("genero")
	public List<String> genero() {
		return Arrays.asList("Masculino", "Femenino");
	}
}
