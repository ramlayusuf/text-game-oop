package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import java.util.ArrayList;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {

    private String topic;
    
    //constructor
    public Status(String topic) {
        this.commandType = CommandType.STATUS;
        this.topic = topic;
    }

    //runs the statuc command depneidng pn the topic, different outputs are given as required
    public String execute(GameState gameState){
        if (topic == null) {
            return "You must specify something to get the status of.";
        }
        
        switch (topic.toLowerCase()){
            case "inventory":
                ArrayList<Item> inventoryList = gameState.getPlayer().getInventory();
                String availableInventory = "";
                for(Item item : inventoryList){
                    availableInventory += item.getName() + " ";
                }
                ArrayList<Equipment> equipment = gameState.getPlayer().getEquipment();
                String availableEquipment = "";
                for(Equipment equip : equipment){
                    availableEquipment += equip.getName() + " ";
                }
                return "Inventory: " + availableInventory + "\n" + "Equipment: " + availableEquipment;
            case "player":
                return "Player: " + gameState.getPlayer().getName() + "\nScore: " + gameState.getPlayer().getScore() + "\n" + gameState.getPlayer().getHealth();
            case "map":
                gameState.getPlayer().subtractFromScore(5);
                return gameState.getMap().display(gameState);
            case "score":
                return "Score: " + gameState.getPlayer().getScore();
            case "health":
                return gameState.getPlayer().getHealth();
            default:
                if(gameState.getPlayer().hasItem(topic)){
                    return gameState.getPlayer().getItem(topic).getDescription();
                }
                if(gameState.getPlayer().hasEquipment(topic)){
                    return gameState.getPlayer().getEquipment(topic).getDescription();
                }
                else{
                    return "";
                }
        }
    }

    @Override
    public String toString(){
        return "Status command: " + topic;
    }
}
