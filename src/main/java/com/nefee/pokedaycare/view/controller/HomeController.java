package com.nefee.pokedaycare.view.controller;

import com.nefee.pokedaycare.logic.manager.DatabaseManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@Getter
@Setter
@ManagedBean (name = "homeController")
public class HomeController {

    @Autowired
    @ManagedProperty (value = "#{databaseManager}")
    private DatabaseManager databaseManager;

    private String output;

    public void onView() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            init();
        }
    }

    public void init() {
    }

    public void generate() {
        output = databaseManager.createDB();
    }

}
