package com.vetallWebapp.demo.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.Date;

@ManagedBean
@RequestScoped
public class AjaxBeanB {
    private String message;

    public String getMessage() {
        return message;
    }

    public void handleEvent(AjaxBehaviorEvent event) {
        UIComponent component = event.getComponent();
        message = component.getFamily()
                + "::" + component.getRendererType()
                + "::" + component.getChildren()
                + " / " + new Date();
    }
}
