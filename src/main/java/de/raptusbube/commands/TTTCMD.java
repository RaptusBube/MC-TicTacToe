package de.raptusbube.commands;

import de.raptusbube.Main;
import de.raptusbube.tictactoe.Game;
import de.raptusbube.tictactoe.GameManager;
import de.raptusbube.tictactoe.GameStatus;
import de.raptusbube.tictactoe.Gui;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class TTTCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("leave")){
                    //if(Main.getMain().getGameManager().isPlayerInAGame(player)){
                        Game game;
                        if((game = Main.getMain().getGameManager().getGameFromPlayer(player))!= null){
                            if(game.isPlayerSpectator(player)){
                                player.sendMessage(Main.getMain().getPrefix()+"Du hast das Spiel verlassen.");
                                player.closeInventory();
                                game.removeSpectator(player);
                            }else{
                                if(game.getPlayer1().equals(player)){
                                    if(game.getPlayer2() == null){
                                        game.stopGame();
                                        game.removePlayer1();
                                        Main.getMain().getGameManager().removeGame(game);
                                        player.sendMessage(Main.getMain().getPrefix()+"Du hast das Spiel verlassen - Das Spiel wird entfernt!");
                                    }else{
                                        //player.getInventory().setContents(game.getPlayerOneContents());
                                        //player.getInventory().setArmorContents(game.getPlayerOneArmor());
                                        //game.setPlayer1(game.getPlayer2());
                                        //game.setNextMove(game.getPlayer2());
                                        player.sendMessage(Main.getMain().getPrefix()+"Du hast das Spiel verlassen!");
                                        if(game.getGameStatus().equals(GameStatus.RUNNING)){
                                            for(Player player1 : game.getAllPlayerInGame()){
                                                player1.sendMessage(Main.getMain().getPrefix()+game.getPlayer2().getName()+" hat das Spiel verlassen!");
                                                player1.sendMessage(Main.getMain().getPrefix()+game.getPlayer1().getName()+" hat gewonnen!");
                                            }
                                            game.stopGame();
                                            Main.getMain().getGameManager().removeGame(game);
                                        }
                                    }
                                }else if(game.getPlayer2().equals(player)){
                                    if(game.getGameStatus().equals(GameStatus.RUNNING)){
                                        game.stopGame();
                                        game.removePlayer2();
                                        player.sendMessage(Main.getMain().getPrefix()+"Du hast das Spiel verlassen!");
                                        for(Player player1 : game.getAllPlayerInGame()){
                                            player1.sendMessage(Main.getMain().getPrefix()+player.getName()+" hat das Spiel verlassen!");
                                        }
                                        Main.getMain().getGameManager().removeGame(game);
                                    }
                                }else{
                                    Main.getMain().getLogger().log(Level.WARNING, "Interner Fehler, konnte Spieler nicht entfernen");
                                }
                            }

                        }
                        player.sendMessage(Main.getMain().getPrefix()+"Du hast das Spiel verlassen!");
                    /*}else{
                        player.sendMessage(Main.getMain().getPrefix()+"Du hast das Spiel verlassen!");
                    }*/
                }else if(args[0].equalsIgnoreCase("play")){
                    Game game;
                    if((game = Main.getMain().getGameManager().joinRandomGame()) != null){
                        player.sendMessage(Main.getMain().getPrefix()+"Du bist einem Spiel beigetreten - es geht sofort los! Dein Mitspieler ist "+game.getPlayer1().getName()+".");
                        for(Player interratePlayer : game.getAllPlayerInGame()){
                            interratePlayer.sendMessage(Main.getMain().getPrefix()+player.getName()+" ist dem Spiel beigetreten. Damit sind ausreichend Spieler da und die Runde beginnt!");
                        }
                        game.setPlayer2(player);
                        game.startGame();
                    }else{
                        player.sendMessage(Main.getMain().getPrefix()+"Es konnte kein freies Spiel gefunden werden. Es wurde ein neuen Spiel erstellt, und es wird auf einen Partner gewartet.");
                        Main.getMain().getGameManager().addGame(new Game(player, false));
                    }
                }else{
                    //System.out.println("Falscher Syntax");
                    player.sendMessage(handleWrongSyntax());
                    //handle wrong Syntax
                }
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("invite")){
                    if(Main.getMain().getGameManager().isPlayerInAGame(player)){
                        player.sendMessage(Main.getMain().getPrefix()+"Du bist bereits in einem Spiel!");
                    }else{
                        Player player1;
                        if((player1 = Bukkit.getPlayer(args[1]))!= null){
                            Main.getMain().getGameManager().addGame(new Game(player, true));
                            player.sendMessage(Main.getMain().getPrefix()+"Du hast "+player1.getDisplayName()+" zu einer Runde TicTacToe eingeladen!");
                            TextComponent message = new TextComponent(Main.getMain().getPrefix()+"Du wurdest von "+player.getDisplayName()+" zu einer Runde TicTacToe eingeladen. ");
                            TextComponent clickMessage = new TextComponent();
                            clickMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ttt join "+player.getName()));
                            clickMessage.addExtra(ChatColor.GREEN+"Klicke hier zum annehmen.");
                            message.addExtra(clickMessage);
                            player1.sendMessage(message.toString());
                            player1.spigot().sendMessage(message);
                        }else{
                            player.sendMessage(Main.getMain().getPrefix()+"Spieler "+args[1]+" nicht gefunden!");
                        }
                        //Main.getMain().getGameManager().addGame(new Game(player, true));
                        //handle invite other player
                        //player.sendMessage(Main.getMain().getPrefix()+"Spieler bereits in einem Spiel!");
                    }
                }else if(args[0].equalsIgnoreCase("join")){
                    Player player1 = Bukkit.getPlayer(args[1]);
                    if(player1 == null){
                        //System.out.println("Spieler nicht gefunden!");
                        player.sendMessage(Main.getMain().getPrefix()+"Spieler "+ args[1] +" nicht gefunden!");
                    }else{
                        Game game;
                        if((game = Main.getMain().getGameManager().joinPlayer(player1)) != null){
                            game.setPlayer2(player);
                            game.startGame();
                        }
                    }
                }else if(args[0].equalsIgnoreCase("spectate")) {
                    //handle ...
                    Player player1;
                    if((player1 = Bukkit.getPlayer(args[1]))!= null){
                        Game game;
                        if((game = Main.getMain().getGameManager().getGameFromPlayer(player1))!= null){
                            game.addSpectator(player);
                            player.openInventory(Gui.getGameInventory(game, "TicTacToe"));
                            player.sendMessage(Main.getMain().getPrefix()+"Du beobachtest nun das Spiel von "+game.getPlayer1()+" vs. "+game.getPlayer2()+".");
                        }else{
                            player.sendMessage(Main.getMain().getPrefix()+"Spieler "+args[1]+" in keinem Spiel!");
                        }
                    }
                }
            }else{
                player.sendMessage(handleWrongSyntax());
            }
        }
        return false;
    }

    private String handleWrongSyntax(){
        return Main.getMain().getPrefix() +" Falscher Syntax! Probiere /ttt <play|leave>  oder /ttt <invite|join|spectate> <Spielername>";
    }
}
