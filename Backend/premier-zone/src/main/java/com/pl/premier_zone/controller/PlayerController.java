package com.pl.premier_zone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.premier_zone.model.Player;
import com.pl.premier_zone.service.PlayerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {
    
    @Autowired
    private PlayerService service;
    
    @GetMapping
    public List<Player> getPlayers(
        @RequestParam(required = false) String team,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String position,
        @RequestParam(required = false) String nation) {
        if(team != null && position != null){
            return service.getPlayersByTeamAndPosition(team, position);
        }
        else if (team != null) {
            return service.getPlayersByTeam(team);
        }
        else if (name != null) {
            return service.getPlayersByName(name);
        }
        else if (position != null) {
            return service.getPlayersByName(position);
        }
        else if (nation != null) {
            return service.getPlayersByName(nation);
        }
        else{
            return service.getAllPlayers();
        }        
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        
        return new ResponseEntity<>(service.addPlayer(player),HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        Player resultPlayer = service.updatePlayer(player);
        if(resultPlayer != null){
            return new ResponseEntity<>(resultPlayer,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        service.deletePlayer(playerName);
        return new ResponseEntity<>("Player Deleted Successfully",HttpStatus.OK);
    }




    
}
