package com.juan.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.juan.springboot.form.app.models.domain.Usuario;

@Component
public class UsuarioValidacion implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario) target;

		// clase Usuario, si es vacio el campo nombre manda error desde
		// messages.properties
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");

		// Otra forma con if
		// if (!usuario.getNombre().isEmpty()) {
		// errors.rejectValue("nombre", "NotEmpty.usuario.nombre");
		// }
		
		if(!usuario.getId().matches("[a-zA-Z]{2}[.][A-Z]{2}[.][A-Z]{1}[.][0-9]{4}[-][a-z]{3}")) {
			errors.rejectValue("id", "Pattern.usuario.id");
		}

	}

}
