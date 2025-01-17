package com.pl.premier_zone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl.premier_zone.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,String>{
    
    void deleteByName(String playerName);
    Optional<Player> findByName(String playerName);

}
