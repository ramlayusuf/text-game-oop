package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import java.util.ArrayList;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {

    //constructor
    public Quit() {
        this.commandType = CommandType.QUIT;
    }

   //runs th quit command, displays the players invetory, equipment, score, and health and the game oer message, and updates the game over check to true so that in the main method the while loop is broken;
    public String execute(GameState gameState){
        ArrayList<Item> inventory = gameState.getPlayer().getInventory();
        String playerInventory = "";
        for(Item item : inventory){
            playerInventory += item.getName() + " " + item.getDescription() + "\n";
        }
        ArrayList<Equipment> equipment = gameState.getPlayer().getEquipment();
        String playerEquipment = "";
        for(Equipment equip : equipment){
            playerEquipment += equip.getName() + " " + equip.getDescription() + "\n";
        }
        gameState.setGameOver(true);
        return "Game over:\nPlayer: " + gameState.getPlayer().getName() + "\nInventory:\n" + playerInventory + "\nEquipment:\n" + playerEquipment + "\nScore: " + gameState.getPlayer().getScore() + "\n" + gameState.getPlayer().getHealth() + "\n\nThank you for playing!\n";
    }
}
