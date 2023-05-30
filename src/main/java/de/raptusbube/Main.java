package de.raptusbube;

import de.raptusbube.commands.TTTCMD;
import de.raptusbube.listener.CloseInventoryListener;
import de.raptusbube.listener.InventoryClickListener;
import de.raptusbube.tictactoe.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static Main main;
    private static GameManager gameManager;

    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("TicTacToe");

    private final String prefix = ChatColor.DARK_GRAY+"["+ChatColor.YELLOW+"TicTacToe"+ChatColor.DARK_GRAY+"] "+ChatColor.GRAY;

    @Override
    public void onEnable(){
        logger.log(Level.FINE, "Lade TicTacToe Plugin");
        main = this;
        gameManager = new GameManager();

        registerEvents();
        registerCommands();
        logger.log(Level.FINE, "TicTacToe Plugin erfolgreich geladen!");

    }

    @Override
    public void onDisable(){
        logger.log(Level.FINE, "TicTacToe Plugin wurde beendet!");
    }


    public static Main getMain() {
        return main;
    }

    public String getPrefix(){
        return prefix;
    }

    public Logger getLogger() {
        return logger;
    }

    public GameManager getGameManager() {
        return gameManager;
    }


    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new CloseInventoryListener(), this);
    }

    private void registerCommands(){
        Objects.requireNonNull(getCommand("tictactoe")).setExecutor(new TTTCMD());
        Objects.requireNonNull(getCommand("ttt")).setExecutor(new TTTCMD());
    }
}