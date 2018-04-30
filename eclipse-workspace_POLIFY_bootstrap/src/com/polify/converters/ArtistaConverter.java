package com.polify.converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.polify.controller.ArtistaBean;
import com.polify.entity.Empresa_difusora;

@FacesConverter(value="ArtistaConverter")
public class ArtistaConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String empresaId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{artistaBean}", ArtistaBean.class);

        ArtistaBean artista = (ArtistaBean)vex.getValue(ctx.getELContext());
        return artista.getEmpresaDifusora(Integer.valueOf(empresaId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object emp) {
        
    	return ((Empresa_difusora)emp).getId_empresa_difusora().toString();
    }	
	
	
	

}
