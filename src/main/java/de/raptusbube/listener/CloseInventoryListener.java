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
        if(event.getPlayer() instanceof  Player player) {
            Game game;
            if((game = Main.getMain().getGameManager().isPlayerInGame(player)) != null){
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), () -> player.openInventory(Gui.getGameInventory(game, "TicTacToe")), 1);
            }
        }
    }
}

