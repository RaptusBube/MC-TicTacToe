package de.raptusbube.tictactoe;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    private final UUID gameId;
    private Player player1;
    private Player player2;

    private Player winner;
    private final List<Player> spectatorList;

    private GameStatus gameStatus;

    private final GameBoard gameBoard;

    private final boolean privateGame;

    public Game(Player player1, boolean privateGame){
        this.gameId = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = null;
        this.spectatorList = new ArrayList<>();
        this.winner = null;
        this.gameStatus = GameStatus.WAITING;
        this.gameBoard = new GameBoard();
        this.privateGame = privateGame;
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

    public void startGame(){
        //open GUI
        //player 1 start
        this.getPlayer1().openInventory(Gui.getGameInventory(this));
        this.getPlayer2().openInventory(Gui.getGameInventory(this));
    }

    public void updateGameBoard(){
        this.getPlayer1().openInventory(Gui.getGameInventory(this));
        this.getPlayer2().openInventory(Gui.getGameInventory(this));
    }

}
