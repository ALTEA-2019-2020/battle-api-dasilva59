package com.miage.altea.battle_api.controller;


import com.miage.altea.battle_api.bo.Battle;
import com.miage.altea.battle_api.bo.BattleBegin;
import com.miage.altea.battle_api.service.BattleService;
import com.miage.altea.battle_api.service.ExceptionNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/battles")
public class BattleController {

    @Autowired
    BattleService battleService;

    @PostMapping
    public UUID setNewBattle(@RequestBody BattleBegin battle) {
        return battleService.getUuid(battle);
    }


    @GetMapping
    public List<Battle> getBattles(){
        return new ArrayList<>(battleService.getBattles().values());

    }
    @GetMapping("/{uuid}")
    public Battle getBattle(@PathVariable UUID uuid){
        return battleService.getBattle(uuid);

    }

    @PostMapping("/{uuid}/{trainerName}/attack")
    public Battle battleAttack(@PathVariable UUID uuid, @PathVariable String trainerName) throws ExceptionNotFound {
        return battleService.attackBattle(uuid, trainerName);
    }



}
