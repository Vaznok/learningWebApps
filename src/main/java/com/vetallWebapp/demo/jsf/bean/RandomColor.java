package com.vetallWebapp.demo.jsf.bean;

import javax.faces.application.Application;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Random;

@ApplicationScoped
@ManagedBean(eager = true) //just an example. To force an application-scoped bean to be instantiated and placed
// in the application scope as soon as the application is started
public class RandomColor {
    public void changeColor (ActionEvent event) {
        UIComponent uiComponent = event.getComponent();

        changeButtonColor(uiComponent);
        addOutput(uiComponent);
    }

    private void changeButtonColor (UIComponent ui) {
        HtmlCommandButton button = (HtmlCommandButton) ui;

        int min = 0x100000;
        int max = 0xFFFFFF;
        int newColor = min + new Random().nextInt(max - min + 1);

        button.setStyle("color:#" + Integer.toHexString(newColor));
        button.setValue("" + Math.random());
    }

    private void addOutput (UIComponent ui) {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        UIOutput bar = (UIOutput) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        bar.setValue("" + System.currentTimeMillis());
        ui.getParent().getChildren().add(bar);
    }
}
