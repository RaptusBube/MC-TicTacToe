package de.raptusbube.listener;

import de.raptusbube.Main;
import de.raptusbube.tictactoe.Game;
import de.raptusbube.tictactoe.GameBoard;
import de.raptusbube.tictactoe.GameStatus;
import de.raptusbube.tictactoe.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof  Player player) {
            Game game;
            if((game = Main.getMain().getGameManager().isPlayerInGame(player)) != null){
                Inventory inventory = event.getInventory();
                if(inventory.getItem(event.getRawSlot())==null){
                    boolean player1 = false;
                    boolean player2 = false;
                    if(game.getPlayer1().equals(player)){
                        player1 = true;
                    }else if(game.getPlayer2().equals(player)){
                        player2 = true;
                    }
                    game.getGameBoard().newInput(player1, player2, event.getRawSlot(), game);
                    game.updateGameBoard();
                }else{
                    event.setCancelled(true);
                }
            }
        }
    }
}
