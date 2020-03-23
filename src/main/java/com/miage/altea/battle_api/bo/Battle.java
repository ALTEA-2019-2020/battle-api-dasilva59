package com.miage.altea.battle_api.bo;

import java.util.UUID;


public class Battle {

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Trainer getOponent() {
        return oponent;
    }

    public void setOponent(Trainer oponent) {
        this.oponent = oponent;
    }

    private UUID uuid;
    private Trainer trainer;
    private Trainer oponent;




}
