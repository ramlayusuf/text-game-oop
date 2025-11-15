package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {
    private String item;

   //constructor, item is parameter for drop method
    public Drop(String item){
        this.item = item;
        this.commandType = CommandType.DROP;
    }

    //runs the command, if the player has the item or equipment it is removed from the inventory and added to the room
    public String execute(GameState gameState){
        Item itemToDrop = gameState.getPlayer().getItem(item);
        Equipment equipmentToDrop = gameState.getPlayer().getEquipment(item);
        if(gameState.getPlayer().hasItem(item)){
            gameState.getPlayer().removeItem(itemToDrop);
            gameState.getMap().getCurrentRoom().addItem(itemToDrop);
            return "You drop: " + item;
        }else if(gameState.getPlayer().hasEquipment(item)){
            gameState.getPlayer().removeEquipment(equipmentToDrop);
            gameState.getMap().getCurrentRoom().addEquipment(equipmentToDrop);
            return "You drop: " + item;
        }else{
            return "You cannot drop " + item;
        }
    }

    @Override
    public String toString(){
        return "Drop command: " + item;
    }
}