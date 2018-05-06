package com.polify.controller.utility;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;



import org.primefaces.component.calendar.Calendar;

@FacesValidator("primeDateRangeValidator")
public class PrimeDateRangeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		// Leave the null handling of startDate to required="true"
		Object startDateValue = component.getAttributes().get("startDate");
		if (startDateValue == null) {
			return;
		}

		Date startDate = (Date) startDateValue;
		Date endDate = (Date) value;
		if (endDate.before(startDate)) {
//			throw new ValidatorException(FacesMessageUtil.newBundledFacesMessage(FacesMessage.SEVERITY_ERROR, "",
//					"msg.dateRange", ((Calendar) component).getLabel(), startDate));
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha ingresada debe ser menor a",
					" No se ha podido crear la operacion");
			throw new ValidatorException(msg);
		}
	}
}
