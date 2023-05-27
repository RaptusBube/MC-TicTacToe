package de.raptusbube.tictactoe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Gui {

    public static Inventory getGameInventory(Game game){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.DISPENSER, "TicTacToe");
        GameBoard gameBoard = game.getGameBoard();
        setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 0);
        setItemInSLot(inventory, gameBoard.getColum2().getField1().getFieldStatus(), 1);
        setItemInSLot(inventory, gameBoard.getColum3().getField1().getFieldStatus(), 2);
        setItemInSLot(inventory, gameBoard.getColum1().getField2().getFieldStatus(), 3);
        setItemInSLot(inventory, gameBoard.getColum2().getField2().getFieldStatus(), 4);
        setItemInSLot(inventory, gameBoard.getColum3().getField2().getFieldStatus(), 5);
        setItemInSLot(inventory, gameBoard.getColum1().getField3().getFieldStatus(), 6);
        setItemInSLot(inventory, gameBoard.getColum2().getField3().getFieldStatus(), 7);
        setItemInSLot(inventory, gameBoard.getColum3().getField3().getFieldStatus(), 8);
        return inventory;
    }

    private static void setItemInSLot(Inventory inventory, GameBoard.Colum.Field.FieldStatus fieldStatus, int slot){
        ItemStack itemStack = null;
        if(fieldStatus.equals(GameBoard.Colum.Field.FieldStatus.CIRCLE)){
            itemStack = new ItemStack(Material.STRUCTURE_VOID);
        }else if(fieldStatus.equals(GameBoard.Colum.Field.FieldStatus.CROSS)){
            itemStack = new ItemStack(Material.BARRIER);
        }
        inventory.setItem(slot, itemStack);
    }

    public static void updateGameBoard(int slot, Game game, Player player){
        GameBoard.Colum.Field.FieldStatus fieldStatus;
        if(game.getPlayer1().equals(player)){
            fieldStatus = GameBoard.Colum.Field.FieldStatus.CIRCLE;
        }else if(game.getPlayer2().equals(player)){
            fieldStatus = GameBoard.Colum.Field.FieldStatus.CROSS;
        }


    }
}
