package com.miage.altea.battle_api.service;
import com.miage.altea.battle_api.bo.Pokemon;
import com.miage.altea.battle_api.bo.PokemonType;
import com.miage.altea.battle_api.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    RestTemplate restTemplate;
    String url;



        public List<PokemonType> listPokemonsTypes() {
      return Arrays.asList(restTemplate.getForObject(
             this.url+"/pokemon-types/", PokemonType[].class));
        }



    @Override
    public List<PokemonType> listPokemon(Trainer t) {
        List<PokemonType> p = this.listPokemonsTypes();
        List<PokemonType> pp=new ArrayList<PokemonType>();
        for(Pokemon pok:t.getTeam()){
            for(PokemonType type:p){
                if(pok.getPokemonTypeId()==type.getId()) {
                    type.setLevel(pok.getLevel());
                    type.getStats().init(pok.getLevel());
                    pp.add(type);
                }
            }
        }
        return pp;
    }

    @Autowired
        void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;

        }

        @Value("${pokemonType.service.url}")
        void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
            this.url=pokemonServiceUrl;

        }
    }

