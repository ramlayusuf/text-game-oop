package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {

    private String item;
    
    //constructor
    public Get(String item) {
        this.item = item;
        this.commandType = CommandType.GET;
    }

    //method hich runs the command, id the item or equipment is in the rom it is added to the players inventory and removed from the room
    public String execute(GameState gameState){
        Item itemToGet = gameState.getMap().getCurrentRoom().getItemByName(item);
        Equipment equipmentToGet = gameState.getMap().getCurrentRoom().getEquipmentByName(item);
        if(gameState.getMap().getCurrentRoom().hasItem(item) && gameState.getMap().getCurrentRoom().getItemByName(item).getHidden() == false){
            if(!gameState.getPlayer().hasItem(item)){
                gameState.getPlayer().addItem(itemToGet);
                gameState.getMap().getCurrentRoom().removeItem(itemToGet);
                return "You pick up: " + item;
            }
            else{
                return "You already have " + item;
            }
        }
        else if(gameState.getMap().getCurrentRoom().hasEquipment(item) && gameState.getMap().getCurrentRoom().getEquipmentByName(item).getHidden() == false){
            if(!gameState.getPlayer().hasEquipment(item)){
                gameState.getPlayer().addEquipment(equipmentToGet);
                gameState.getMap().getCurrentRoom().removeEquipment(equipmentToGet);
                return "You pick up: " + item;
            }
            else{
                return "You already have " + item;
            }
        }
        else{
            return "No " + item + " to get.";
        }
    }

    @Override
    public String toString(){
        return "Get command: " + item;
    }
}
