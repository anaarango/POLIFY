package com.polify.controller.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorEmail implements Validator {
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {

		String emailIngresado = (String) arg2;

		Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailIngresado);
		if(!matcher.find()){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email invalido",
					"El email: " + emailIngresado + " no es un email valido");
			throw new ValidatorException(msg);
		}
		
		
	}
}
