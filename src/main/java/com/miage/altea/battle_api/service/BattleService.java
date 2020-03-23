package com.miage.altea.battle_api.service;


import com.miage.altea.battle_api.bo.Battle;
import com.miage.altea.battle_api.bo.BattleBegin;
import com.miage.altea.battle_api.bo.PokemonType;
import com.miage.altea.battle_api.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class BattleService {

    private HashMap<UUID, Battle>  battles=new HashMap<UUID,Battle>();
    @Autowired
    TrainerService trainerService;
    @Autowired
    PokemonTypeServiceImpl pokemonTypeService;


    public HashMap<UUID, Battle> getBattles() {
        return battles;
    }



    public UUID getUuid(BattleBegin b){
        Battle newBattle= new Battle();
         UUID uuid= UUID.randomUUID();
        newBattle.setUuid(uuid);
        Trainer t=trainerService.trainer(b.getTrainer1());
        t.setTeamtype(pokemonTypeService.listPokemon(t));
        newBattle.setTrainer(t);
        Trainer tt=trainerService.trainer(b.getTrainer2());
        tt.setTeamtype(pokemonTypeService.listPokemon(tt));
        newBattle.setOponent(tt);
        this.battles.put(uuid,newBattle);
        return uuid;

    }


    public Battle getBattle(UUID uuid) {
        return this.battles.get(uuid);
    }

    public Battle attackBattle(UUID uuid, String trainerName) throws ExceptionNotFound {

        Battle b=this.battles.get(uuid);
        Trainer attack = b.getTrainer().getName().equals(trainerName) ? b.getTrainer() : b.getOponent();
        Trainer defense = b.getTrainer().getName().equals(trainerName) ? b.getOponent() : b.getTrainer();
       List<PokemonType> attackTeam=attack.getTeamtype();
       List<PokemonType> defenseTeam=defense.getTeamtype();

       PokemonType attackPokememon=this.getPokemon(attackTeam);
       PokemonType defensePokemon=this.getPokemon(defenseTeam);
       if(attackPokememon==null || defensePokemon==null ){throw new ExceptionNotFound();}

       if(b.getNewBattle()){
           if(attackPokememon.getStats().getSpeed()<defensePokemon.getStats().getSpeed()){
               PokemonType temp=attackPokememon;
               attackPokememon=defensePokemon;
               defensePokemon=temp;
               Trainer tem=attack;
               attack=defense;
               defense=tem;
               attack.setNextTurn(true);
               defense.setNextTurn(false);

           }
           else{
               attack.setNextTurn(true);
               defense.setNextTurn(false);
           }
           b.setNewBattle(false);
       }

        if(!attack.getNextTurn()){ throw new ExceptionNotFound();}


       int score = 2+((2*attackPokememon.getLevel()/5+
               (2*(attackPokememon.getStats().getAttack()/defensePokemon.getStats().getDefense()))));
       int defenseHp=defensePokemon.getStats().getHp();
       int total=defenseHp-score;
        defensePokemon.getStats().setHp(total <= 0 ? 0 : total);
        defensePokemon.setAlive(total <= 0 ? false : true);
        attack.setNextTurn(false);
        defense.setNextTurn(true);
        return b;

    }


private PokemonType getPokemon(List<PokemonType> pokemons){
        for(PokemonType p:pokemons) {
            if (p.getAlive())
                return p;
        }
        return null;
}



}
