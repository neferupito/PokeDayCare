package com.nefee.pokedaycare.view.controller;

import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import com.nefee.pokedaycare.logic.manager.DatabaseManager;
import com.nefee.pokedaycare.logic.manager.PokemonManager;
import com.nefee.pokedaycare.logic.model.Pokemon;
import com.nefee.pokedaycare.view.utils.FacesUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Optional;

@Getter
@Setter
@ManagedBean(name = "pokemonPageController")
@ViewScoped
public class PokemonPageController {

    @Autowired
    @ManagedProperty(value = "#{pokemonManager}")
    private PokemonManager pokemonManager;

    private String name;

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
        Optional<Pokemon> optional = pokemonManager.findByName(name);

        if (optional.isPresent()) {
            pokemon = optional.get();
        } else {
            FacesUtils.addErrorMessage("No Pokemon found");
        }

    }

}
