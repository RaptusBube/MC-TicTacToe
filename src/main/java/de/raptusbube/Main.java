package de.raptusbube;

import de.raptusbube.commands.TTTCMD;
import de.raptusbube.listener.InventoryClickListener;
import de.raptusbube.tictactoe.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    private static Main main;
    private static GameManager gameManager;

    @Override
    public void onEnable(){
        main = this;
        gameManager = new GameManager();

        registerEvents();
        registerCommands();

    }

    @Override
    public void onDisable(){

    }


    public static Main getMain() {
        return main;
    }

    public GameManager getGameManager() {
        return gameManager;
    }


    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryClickListener(), this);
    }

    private void registerCommands(){
        Objects.requireNonNull(getCommand("tictactoe")).setExecutor(new TTTCMD());
        Objects.requireNonNull(getCommand("ttt")).setExecutor(new TTTCMD());
    }
}