package com.miage.altea.battle_api.service;
import com.miage.altea.battle_api.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrainerServiceImpl implements TrainerService {

    RestTemplate restTemplate;
    String url;

        @Autowired
        @Qualifier("trainerApiRestTemplate")
        void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;

        }

        @Value("${trainerType.service.url}")
        void setTrainerTypeServiceUrl(String pokemonServiceUrl) {
            this.url=pokemonServiceUrl;

        }

    @Override
    public Trainer trainer(String name) {

            return restTemplate.getForObject(this.url+"/trainers/"+name, Trainer.class);
    }
}

