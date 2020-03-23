package com.miage.altea.battle_api.bo;

import java.util.List;

public class Trainer {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }


    public void setNextTurn(boolean next) {
        this.nextTurn  = next;
    }

    public Boolean getNextTurn(){
        return this.nextTurn;
    }
    private String name;
    private List<Pokemon> team;
    private boolean nextTurn;

    public List<PokemonType> getTeamtype() {
        return teamtype;
    }

    public void setTeamtype(List<PokemonType> teamtype) {
        this.teamtype = teamtype;
    }

    private List<PokemonType> teamtype;
}
