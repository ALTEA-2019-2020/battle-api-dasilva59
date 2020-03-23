package com.miage.altea.battle_api.service;

import com.miage.altea.battle_api.bo.PokemonType;
import com.miage.altea.battle_api.bo.Trainer;
import java.util.List;

public interface  PokemonTypeService {

    List<PokemonType> listPokemonsTypes();
    List<PokemonType> listPokemon(Trainer t);
}
