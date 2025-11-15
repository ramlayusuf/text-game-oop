package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import java.util.ArrayList;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {

    private String target;
    
    //constructor method
    public Look(String target){
        this.target = target;
        this.commandType = CommandType.LOOK;
    }

    //runs the command
    public String execute(GameState gameState){
        if (target == null) { //user didnt enter target
            return "You must specify something to look at.";
        }
        
        if("room".equalsIgnoreCase(target)){ //look room, displayes items and equipment visible in the room, as well as room name and description
            ArrayList<Item> items = gameState.getMap().getCurrentRoom().getItems();
            String visibleItems = "";
            ArrayList<Equipment> equipments = gameState.getMap().getCurrentRoom().getEquipments();
            String visibleEquipment = "";
            for(Item item : items){
                if(!item.getHidden()){
                    visibleItems += "->" + item.getName() + ": " + item.getDescription() + "\n";
                }
            }
            for(Equipment equipment : equipments){
                if(!equipment.getHidden()){
                    visibleEquipment += "->" + equipment.getName() + ": " + equipment.getDescription() + "\n";
                }
            }
            if(visibleItems.isEmpty()){
                visibleItems = "No visible items.";
            }
            if(visibleEquipment.isEmpty()){
                visibleEquipment = "No visible equipment.";
            }
            return "You are at: " + gameState.getMap().getCurrentRoom().getName() + "\n" + gameState.getMap().getCurrentRoom().getDescription() + "\nItems:\n" + visibleItems + "\nEquipment:\n" + visibleEquipment;
        }
        else if("exits".equalsIgnoreCase(target)){ //look exits displayes the names and directiosn of all the visible exits in the room
            ArrayList<Exit> exits = gameState.getMap().getCurrentRoom().getExits();
            String visibleExits = "";
            for(Exit exit : exits){
                if(!exit.getHidden()){
                    visibleExits += exit.getName() + ": " + exit.getDescription() + "\n";
                }
            }
            if(visibleExits.isEmpty()){
                return "There are no visible exits.";
            }else{
                return "The available exits are:\n" + visibleExits;
            }
        }
        else if("features".equalsIgnoreCase(target)){ //look features displays all visible features in the room
            ArrayList<Feature> features = gameState.getMap().getCurrentRoom().getFeatures();
            String visibleFeatures = "";
            for(Feature feature : features){
                if(!feature.getHidden()){
                    visibleFeatures += "->" + feature.getName() + ": " + feature.getDescription() + "\n";
                }
            }
            if(visibleFeatures.isEmpty()){
                return "No visible features.";
            }
            return "You also see:\n" + visibleFeatures;
        }
        else{ //look any game object returns a description of that game object
            for(GameObject gameObject : gameState.getMap().getCurrentRoom().getAll()){
                if(target.equalsIgnoreCase(gameObject.getName())){
                    if(!gameObject.getHidden()){
                        return gameObject.getDescription();
                    }
                }
            }
            return ""; //if no game object found with that name then the look command is invalid, returns an empty string as required by the look test
        }
    }

    @Override
    public String toString(){
        return "Look command: " + target;
    }
}
