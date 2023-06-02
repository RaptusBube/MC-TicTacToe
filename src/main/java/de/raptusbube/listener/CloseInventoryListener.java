package de.raptusbube.listener;

import de.raptusbube.Main;
import de.raptusbube.tictactoe.Game;
import de.raptusbube.tictactoe.Gui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CloseInventoryListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if(event.getPlayer() instanceof Player player) {
            Game game;
            if((game = Main.getMain().getGameManager().isPlayerInGame(player)) != null){
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), () -> player.openInventory(Gui.getGameInventory(game, "TicTacToe")), 8);
                /*player.sendMessage(Main.getMain().getPrefix()+"Du hast das Spiel verlassen!");
                Player player1 = player;
                if(game.getPlayer1().equals(player)){
                    player1 = game.getPlayer1();
                }else if(game.getPlayer2().equals(player)){
                    player1 = game.getPlayer2();
                }

                if(game.getGameStatus().equals(GameStatus.RUNNING)){
                    for(Player player2 : game.getAllPlayerInGame()){
                        player2.sendMessage(Main.getMain().getPrefix()+player.getName()+" hat das Spiel verlassen!");
                        player2.sendMessage(Main.getMain().getPrefix()+player1.getName()+" hat gewonnen!");
                    }
                    game.stopGame();
                    Main.getMain().getGameManager().removeGame(game);
                }*/
            }
        }
    }
}

