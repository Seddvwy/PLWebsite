package com.pl.premier_zone.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pl.premier_zone.model.Player;
import com.pl.premier_zone.repository.PlayerRepository;

import jakarta.transaction.Transactional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository repo;
    
    public List<Player> getAllPlayers(){
        return repo.findAll();
    }

    public List<Player> getPlayersByTeam(String teamName){
        return repo.findAll().stream().filter(player -> teamName.equals(player.getTeam()))
        .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String searchText){
        return repo.findAll().stream().filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()))
        .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPos(String searchText){
        return repo.findAll().stream().filter(player -> player.getPosition().toLowerCase().contains(searchText.toLowerCase()))
        .collect(Collectors.toList());
    }

    public List<Player> getPlayersByNation(String searchText){
        return repo.findAll().stream().filter(player -> player.getNation().toLowerCase().contains(searchText.toLowerCase()))
        .collect(Collectors.toList());
    }

    public List<Player> getPlayersByTeamAndPosition(String team, String position){
        return repo.findAll().stream().filter(player -> team.equals(player.getTeam() )&& position.equals(player.getPosition()))
        .collect(Collectors.toList());
    }

    public Player addPlayer(Player player){
        repo.save(player);
        return player;
    }

   
    public Player updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer = repo.findByName(updatedPlayer.getName());
        if (existingPlayer.isPresent()){
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setTeam(updatedPlayer.getTeam());
            playerToUpdate.setPosition(updatedPlayer.getPosition());
            playerToUpdate.setNation(updatedPlayer.getNation());
            
            repo.save(playerToUpdate);
            return playerToUpdate;
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String playerName){
        repo.deleteByName(playerName);
    }

}
