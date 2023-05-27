package de.raptusbube.tictactoe;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private List<Game> games;

    public GameManager(){
        this.games = new ArrayList<>();
    }

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game){
        games.add(game);
    }

    public void removeGame(Game game){
        games.remove(game);
    }

    public boolean isPlayerInAGame(Player player){
        for(Game game : games){
            if(game.getPlayer1().equals(player) || game.getPlayer2().equals(player)){
                return true;
            }
        }
        return false;
    }

    public Game getGameFromPlayer(Player player){
        for(Game game : games){
            if(game.getPlayer1().equals(player) || game.getPlayer2().equals(player)){
                return game;
            }
        }
        return null;
    }

    public Game joinPlayer(Player player){
        for(Game game : games){
            if(game.getPlayer1().equals(player) && game.getPlayer2() == null){
                return game;
            }
        }
        return null;
    }

    public Game joinRandomGame(){
        for(Game game : games){
            if(game.getPlayer1() != null  && game.getPlayer2() == null && !game.isPrivateGame()){
                return game;
            }
        }
        return null;
    }

    public Game isPlayerInGame(Player player){
        for(Game game : games){
            if(game.getPlayer1().equals(player) || game.getPlayer2().equals(player)){
                return game;
            }
        }
        return null;
    }

}
