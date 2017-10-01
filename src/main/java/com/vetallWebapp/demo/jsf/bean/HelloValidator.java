package com.vetallWebapp.demo.jsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("HelloMessageValidator")
public class HelloValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String message = o.toString();
        if(message.startsWith("abc")) {
            FacesMessage facesMessage = new FacesMessage(
              "Hello message validator failed",
                    "I don't like starting with 'abc'"
            );
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }
}
