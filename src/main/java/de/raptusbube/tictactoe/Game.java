package de.raptusbube.tictactoe;

import de.raptusbube.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Game {
    private final UUID gameId;
    private Player player1;
    private Player player2;

    private Player nextMove;

    private Player winner;
    private final List<Player> spectatorList;

    private GameStatus gameStatus;

    private final GameBoard gameBoard;

    private final boolean privateGame;

    private ItemStack[] playerOneArmor;

    private ItemStack[] playerOneContents;

    private ItemStack[] playerTwoArmor;

    private ItemStack[] playerTwoContents;

    public Game(Player player1, boolean privateGame){
        this.gameId = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = null;
        this.spectatorList = new ArrayList<>();
        this.winner = null;
        this.gameStatus = GameStatus.WAITING;
        this.gameBoard = new GameBoard();
        this.privateGame = privateGame;
        this.nextMove = player1;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    public void setPlayer1(Player player1) {
        this.player1 = player1;

    }

    public void setNextMove(Player nextMove) {
        this.nextMove = nextMove;
    }

    public void removePlayer1(){
        this.player1 = null;
    }

    public void removePlayer2(){
        this.player2 = null;
    }

    public void addSpectator(Player spectator) {
        this.spectatorList.add(spectator);
    }

    public void removeSpectator(Player spectator) {
        this.spectatorList.remove(spectator);
    }

    public boolean isPlayerSpectator(Player spectator) {
        return this.spectatorList.contains(spectator);
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public UUID getGameId() {
        return gameId;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getNextMove() {
        return nextMove;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public ItemStack[] getPlayerOneArmor() {
        return playerOneArmor;
    }

    public ItemStack[] getPlayerOneContents() {
        return playerOneContents;
    }

    public ItemStack[] getPlayerTwoArmor() {
        return playerTwoArmor;
    }

    public ItemStack[] getPlayerTwoContents() {
        return playerTwoContents;
    }

    public List<Player> getSpectatorList() {
        return spectatorList;
    }

    public Player getWinner() {
        return winner;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isPrivateGame() {
        return privateGame;
    }

    public List<Player> getAllPlayerInGame(){
        List<Player> list = new ArrayList<>();
        if(player1 != null) list.add(player1);
        if(player2 != null) list.add(player2);
        list.addAll(getSpectatorList());
        return list;
    }

    public void startGame(){
        this.gameStatus = GameStatus.RUNNING;
        this.playerOneArmor = player1.getInventory().getArmorContents();
        this.playerOneContents = player1.getInventory().getContents();
        this.playerTwoArmor = player2.getInventory().getArmorContents();
        this.playerTwoContents = player2.getInventory().getContents();
        this.getPlayer1().getInventory().clear();
        this.getPlayer2().getInventory().clear();
        this.getPlayer1().openInventory(Gui.getGameInventory(this, "TicTacToe"));
        this.getPlayer2().openInventory(Gui.getGameInventory(this, "TicTacToe"));
        for(Player player : getSpectatorList()){
            player.openInventory(Gui.getGameInventory(this, "TicTacToe"));
        }
    }

    public void stopGame(){
        this.gameStatus = GameStatus.FINISHED;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), () -> {
            this.getPlayer1().getInventory().setArmorContents(playerOneArmor);
            this.getPlayer1().getInventory().setContents(playerOneContents);
            this.getPlayer2().getInventory().setArmorContents(playerTwoArmor);
            this.getPlayer2().getInventory().setContents(playerTwoContents);

            this.getPlayer1().closeInventory();
            this.getPlayer2().closeInventory();
            for(Player player : getSpectatorList()){
                player.openInventory(Gui.getGameInventory(this, "TicTacToe"));
            }
        }, 20*4);

    }

    public void updateGameBoard(){
        this.getPlayer1().openInventory(Gui.getGameInventory(this, "TicTacToe"));
        this.getPlayer2().openInventory(Gui.getGameInventory(this, "TicTacToe"));
        for(Player player : getSpectatorList()){
            player.openInventory(Gui.getGameInventory(this, "TicTacToe"));
        }
    }

    private int scheduler;

    private boolean cycle = false;

    public void visualizeWinner(int i){
        final int[] count = {0};
        scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), () -> {
            for(Player player : getAllPlayerInGame()){
                player.openInventory(Gui.getWinningVisualization(this, i, cycle));
            }
            if(count[0] >= 7){
                Bukkit.getServer().getScheduler().cancelTask(scheduler);
            }
            cycle = !cycle;
            count[0]++;
        }, 1, 10);
    }


}
