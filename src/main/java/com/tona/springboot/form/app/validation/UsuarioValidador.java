package com.tona.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tona.springboot.form.app.models.domain.Usuario;


@Component
public class UsuarioValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		Usuario usuario=(Usuario)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre","requerido.usuario.nombre" );
		
//		Otra forma de validar de validar con if en vez de usar rejectIfEmpty
//		if (usuario.getNombre().isEmpty()) {
//			errors.rejectValue("nombre", "nombre","NotEmpty.usuario.nombre");
//		}
		
//		if (!usuario.getIdentificador().matches("[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")) {
//			errors.rejectValue("identificador", "pattern.usuario.identificador");
//		}

	}

}
