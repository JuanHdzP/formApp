package com.juan.springboot.form.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentificadoRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if(value.matches("[a-zA-Z]{2}[.][A-Z]{2}[.][A-Z]{1}[.][0-9]{4}[-][a-z]{3}")) {
			return true;
		}
		return false;
	}

}
