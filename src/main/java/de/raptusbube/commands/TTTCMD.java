package de.raptusbube.commands;

import de.raptusbube.Main;
import de.raptusbube.tictactoe.Game;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TTTCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("leave")){
                    if(Main.getMain().getGameManager().isPlayerInAGame(player)){
                        Game game;
                        if((game = Main.getMain().getGameManager().getGameFromPlayer(player))!= null){
                            if(game.getPlayer1().equals(player)){
                                game.removePlayer1();
                                if(game.getPlayer2() == null){
                                    Main.getMain().getGameManager().removeGame(game);
                                }else{
                                    game.setPlayer1(game.getPlayer2());
                                    game.removePlayer2();
                                }
                            }else if(game.getPlayer2().equals(player)){
                                game.removePlayer2();
                                //stop game
                                Main.getMain().getGameManager().removeGame(game);
                            }else{
                                System.out.println("Interner Fehler, konnte Spieler nicht entfernen");
                            }
                        }
                        System.out.println("Du hast das Spiel verlassem!");
                    }else{
                        System.out.println("Du bist in keinem Spiel!");
                    }
                }else if(args[0].equalsIgnoreCase("play")){
                    Game game;
                    if((game = Main.getMain().getGameManager().joinRandomGame()) != null){
                        game.setPlayer2(player);
                        game.startGame();
                    }else{
                        System.out.println("Es konnte kein freies Spiel gefunden werden. Es wurde ein neuen Spiel für Dich erstellt, und es wird auf einen Partner gewartet.");
                        Main.getMain().getGameManager().addGame(new Game(player, false));
                    }
                }else{
                    System.out.println("Falscher Syntax");
                    //handle wrong Syntax
                }
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("invite")){
                    if(Main.getMain().getGameManager().isPlayerInAGame(player)){
                        System.out.println("Spieler bereits in einem Spiel!");
                    }else{
                        Main.getMain().getGameManager().addGame(new Game(player, true));
                        //handle invite other player
                        System.out.println("DU wurdest einem Spiel hinzugefügt!");
                    }
                }else if(args[0].equalsIgnoreCase("join")){
                    Player player1 = Bukkit.getPlayer(args[1]);
                    if(player1 == null){
                        System.out.println("Spieler nicht gefunden!");
                    }else{
                        Game game;
                        if((game = Main.getMain().getGameManager().joinPlayer(player1)) != null){
                            game.setPlayer2(player);
                            game.startGame();
                        }
                    }
                }else if(args[0].equalsIgnoreCase("spectate")) {
                    //handle ...
                }
            }else{
                System.err.println("Falscher Syntax");
            }
        }
        return false;
    }
}
