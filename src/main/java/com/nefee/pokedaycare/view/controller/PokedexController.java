package com.nefee.pokedaycare.view.controller;

import com.nefee.pokedaycare.logic.manager.PokemonManager;
import com.nefee.pokedaycare.logic.model.Pokemon;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

@Getter
@Setter
@ManagedBean (name = "pokedexController")
public class PokedexController {

    @Autowired
    @ManagedProperty (value = "#{pokemonManager}")
    private PokemonManager pokemonManager;

    private List<Pokemon> allPokemons;

    public void onView() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            init();
        }
    }

    public void init() {
        loadPokemons();
    }

    public void loadPokemons() {
        if (allPokemons == null || allPokemons.isEmpty()) {
            allPokemons = pokemonManager.findAllByGeneration(null);
        }
    }

    public List<Pokemon> getAllPokemons() {
//        if (allPokemons == null || allPokemons.isEmpty()) {
//            loadPokemons();
//        }
        return allPokemons;
    }

}
