package de.raptusbube.tictactoe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class Gui {

    public static Inventory getGameInventory(Game game, String title){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.DROPPER, title);
        GameBoard gameBoard = game.getGameBoard();
        setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 0, game);
        setItemInSLot(inventory, gameBoard.getColum2().getField1().getFieldStatus(), 1, game);
        setItemInSLot(inventory, gameBoard.getColum3().getField1().getFieldStatus(), 2, game);
        setItemInSLot(inventory, gameBoard.getColum1().getField2().getFieldStatus(), 3, game);
        setItemInSLot(inventory, gameBoard.getColum2().getField2().getFieldStatus(), 4, game);
        setItemInSLot(inventory, gameBoard.getColum3().getField2().getFieldStatus(), 5, game);
        setItemInSLot(inventory, gameBoard.getColum1().getField3().getFieldStatus(), 6, game);
        setItemInSLot(inventory, gameBoard.getColum2().getField3().getFieldStatus(), 7, game);
        setItemInSLot(inventory, gameBoard.getColum3().getField3().getFieldStatus(), 8, game);
        return inventory;
    }

    private static void setItemInSLot(Inventory inventory, GameBoard.Colum.Field.FieldStatus fieldStatus, int slot, Game game){
        ItemStack itemStack = null;
        if(fieldStatus.equals(GameBoard.Colum.Field.FieldStatus.CIRCLE)){
            itemStack = new ItemStack(Material.STRUCTURE_VOID);
            ItemMeta itemMeta = itemStack.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName(game.getPlayer1().getDisplayName());
            itemStack.setItemMeta(itemMeta);

        }else if(fieldStatus.equals(GameBoard.Colum.Field.FieldStatus.CROSS)){
            itemStack = new ItemStack(Material.BARRIER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName(game.getPlayer2().getDisplayName());
            itemStack.setItemMeta(itemMeta);
        }
        inventory.setItem(slot, itemStack);
    }

    public static Inventory getWinningVisualization(Game game, int i, boolean cycle){
        Inventory inventory = getGameInventory(game, ChatColor.GREEN + game.getWinner().getDisplayName()+" hat gewonnen!");
        GameBoard gameBoard = game.getGameBoard();
        switch (i) {
            case 0 -> {
                if (cycle) {
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 0, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 3, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 6, game);
                } else {
                    inventory.setItem(0, null);
                    inventory.setItem(3, null);
                    inventory.setItem(6, null);
                }
            }
            case 1 -> {
                if (cycle) {
                    setItemInSLot(inventory, gameBoard.getColum1().getField2().getFieldStatus(), 1, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField2().getFieldStatus(), 4, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField2().getFieldStatus(), 7, game);
                } else {
                    inventory.setItem(1, null);
                    inventory.setItem(4, null);
                    inventory.setItem(7, null);
                }
            }
            case 2 -> {
                if (cycle) {
                    setItemInSLot(inventory, gameBoard.getColum1().getField3().getFieldStatus(), 2, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField3().getFieldStatus(), 5, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField3().getFieldStatus(), 8, game);
                } else {
                    inventory.setItem(2, null);
                    inventory.setItem(5, null);
                    inventory.setItem(8, null);
                }
            }
            case 3 -> {
                if (cycle) {
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 0, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 1, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 2, game);
                } else {
                    inventory.setItem(0, null);
                    inventory.setItem(1, null);
                    inventory.setItem(2, null);
                }
            }
            case 4 -> {
                if (cycle) {
                    setItemInSLot(inventory, gameBoard.getColum2().getField2().getFieldStatus(), 3, game);
                    setItemInSLot(inventory, gameBoard.getColum2().getField2().getFieldStatus(), 4, game);
                    setItemInSLot(inventory, gameBoard.getColum2().getField2().getFieldStatus(),5, game);
                } else {
                    inventory.setItem(3, null);
                    inventory.setItem(4, null);
                    inventory.setItem(5, null);
                }
            }
            case 5 -> {
                if (cycle) {
                    setItemInSLot(inventory, gameBoard.getColum3().getField3().getFieldStatus(), 6, game);
                    setItemInSLot(inventory, gameBoard.getColum3().getField3().getFieldStatus(), 7, game);
                    setItemInSLot(inventory, gameBoard.getColum3().getField3().getFieldStatus(), 8, game);
                } else {
                    inventory.setItem(6, null);
                    inventory.setItem(7, null);
                    inventory.setItem(8, null);
                }
            }
            case 6 -> {
                if (cycle) {
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 0, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 4, game);
                    setItemInSLot(inventory, gameBoard.getColum1().getField1().getFieldStatus(), 8, game);
                } else {
                    inventory.setItem(0, null);
                    inventory.setItem(4, null);
                    inventory.setItem(8, null);
                }
            }
            case 7 -> {
                if (cycle) {
                    setItemInSLot(inventory, gameBoard.getColum2().getField2().getFieldStatus(), 2, game);
                    setItemInSLot(inventory, gameBoard.getColum2().getField2().getFieldStatus(), 4, game);
                    setItemInSLot(inventory, gameBoard.getColum2().getField2().getFieldStatus(), 6, game);
                } else {
                    inventory.setItem(2, null);
                    inventory.setItem(4, null);
                    inventory.setItem(6, null);
                }
            }
        }
        return inventory;
    }
}
