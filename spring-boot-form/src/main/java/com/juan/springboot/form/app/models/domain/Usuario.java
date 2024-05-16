package com.juan.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

//import org.springframework.format.annotation.DateTimeFormat;

import com.juan.springboot.form.app.validation.IdentificadorRegex;
import com.juan.springboot.form.app.validation.Requerido;

//import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Usuario {

	// @Pattern(regexp = "[a-zA-Z]{2}[.][A-Z]{2}[.][A-Z]{1}[.][0-9]{4}[-][a-z]{3}")
	@IdentificadorRegex // Anotacion custom
	private String id;

	// @NotEmpty(message = "El nombre es requerido")
	private String nombre;

	// @NotEmpty(message = "El apellido es requerido")
	@Requerido // Anotacion custom
	private String apellido;

	// @NotEmpty
	@NotBlank(message = "El username es requerido")
	@Size(min = 3, max = 10)
	private String username;

	@NotEmpty
	@Email(message = "Email con formato incorrecto")
	private String email;

	@NotEmpty(message = "La contraseña es requerida")
	private String password;

	@NotNull
	@Min(5)
	@Max(5000)
	private Integer cuenta;

	@NotNull
	@Past
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;

	// Antes con paises de un List o un map
	// @NotEmpty
	// private String pais;

	// Ahora con una clase Pais
	// @Valid //Para validar campos de un Obj relacionado
	@NotNull // Ahora notnull ya que estamos validando sobre el objeto con el editor en el
				// initbinder
	private Pais pais;

	
	//public String getPais() { return pais; }
	//public void setPais(String pais) { this.pais = pais; }
	 
	// Antes con paises de un List o un map
	//@NotEmpty
	//private List<String> roles;

	// Ahora con una clase Role
	@NotEmpty
	private List<Role> roles;
	
	 // public List<String> getRoles() { return roles; }
	 // public void setRoles(List<String> roles) { this.roles = roles; }
	
	private Boolean terminos;
	
	@NotEmpty
	private String genero;

	private String valorSecreto;
	
	
	
	public String getValorSecreto() {
		return valorSecreto;
	}

	public void setValorSecreto(String valorSecreto) {
		this.valorSecreto = valorSecreto;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Boolean getTerminos() {
		return terminos;
	}

	public void setTerminos(Boolean terminos) {
		this.terminos = terminos;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public String getUsername() {
		return username;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
