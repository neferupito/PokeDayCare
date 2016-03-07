package com.nefee.pokedaycare.view.controller;

import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "homeController")
@ViewScoped
public class HomeController {

    public void onView() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            init();
        }
    }

    public void init() {
    }

}
