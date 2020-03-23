package com.miage.altea.battle_api.bo;

public class Pokemon {
    public int getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(int pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    private int pokemonTypeId;

    private int level;

}
