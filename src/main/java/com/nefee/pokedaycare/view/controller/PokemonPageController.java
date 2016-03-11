package com.nefee.pokedaycare.view.controller;

import com.nefee.pokedaycare.logic.manager.PokemonManager;
import com.nefee.pokedaycare.logic.model.Pokemon;
import com.nefee.pokedaycare.view.utils.FacesUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.Optional;

@Getter
@Setter
@ManagedBean (name = "pokemonPageController")
public class PokemonPageController {

    @Autowired
    @ManagedProperty (value = "#{pokemonManager}")
    private PokemonManager pokemonManager;

    private Integer nationalId;

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
        if (pokemon == null) {
            Optional<Pokemon> optional = pokemonManager.findByNationalIdAndGeneration(nationalId, null);

            if (optional.isPresent()) {
                pokemon = optional.get();
            } else {
                FacesUtils.addErrorMessage("No Pokemon found");
            }
        }
    }

}
