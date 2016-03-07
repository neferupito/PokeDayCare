package com.nefee.pokedaycare.view.controller;

import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import com.nefee.pokedaycare.logic.manager.impl.PokemonManagerImpl;
import com.nefee.pokedaycare.logic.model.Pokemon;
import com.nefee.pokedaycare.view.utils.FacesUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@Getter
@Setter
@ManagedBean(name = "pokemonPageController")
@ViewScoped
public class PokemonPageController {

    @ManagedProperty(value = "#{pokemonManager}")
    private PokemonManagerImpl pokemonManager;

    private Pokemon pokemon;

    public void onView() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            init();
        }
    }

    public void init() {
        loadPokemon();
    }

    public void loadPokemon() {
        try {
            pokemon = pokemonManager.findByName("Pikachu");
        } catch (PokeDayCareException pdce) {
            pdce.printStackTrace();
            FacesUtils.addErrorMessage(pdce.getMessage());
        }
    }

}
