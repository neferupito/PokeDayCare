package com.nefee.pokedaycare.view.controller;

import com.nefee.pokedaycare.logic.exception.BreedingNotPossibleException;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.manager.BreedManager;
import com.nefee.pokedaycare.logic.model.BreedCase;
import com.nefee.pokedaycare.view.utils.FacesUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@Getter
@Setter
@ManagedBean (name = "breedController")
public class BreedController {

    @Autowired
    @ManagedProperty (value = "#{breedManager}")
    private BreedManager breedManager;

    private BreedCase breedCase;

    private Integer nationalId;

    public void onView() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            init();
        }
    }

    public void init() {
        loadBreedCase();
    }

    public void loadBreedCase() {

        if (breedCase == null) {
            try {
                breedCase = breedManager.buildBreedCase(nationalId, null);
            } catch (PokeNotFoundException | BreedingNotPossibleException e) {
                FacesUtils.addErrorMessage(e.getMessage());
            }

        }

    }
}
