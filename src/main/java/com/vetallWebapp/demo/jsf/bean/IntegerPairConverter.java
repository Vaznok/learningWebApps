package com.vetallWebapp.demo.jsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;

@FacesConverter("IntegerPairConverter")
public class IntegerPairConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            int fst = Integer.parseInt(s.substring(0, s.indexOf("-")));
            System.out.println(fst);
            int scd = Integer.parseInt(s.substring((s.indexOf("-") + 1), s.length()));
            System.out.println(scd);
            return new IntegerPair(fst, scd);
        } catch (RuntimeException e) {

            FacesMessage facesMessage = new FacesMessage(
                    "Bad Integer value",
                    "Integer must have format 123-345"
            );
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(facesMessage);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        IntegerPair pair = (IntegerPair) o;
        return pair.getFst() + "-" + pair.getScd();
    }
}
