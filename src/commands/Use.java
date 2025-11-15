package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {

    private String equipmentName;
    private String target;
    private String preposition;
    
    //multiple cobstructors of use command due to it being called different eays, eg use antidote vs use key on chest
    public Use(String equipmentName, String target) {
        this.commandType = CommandType.USE;
        this.equipmentName = equipmentName;
        this.target = target;
        this.preposition = "";
    }

     public Use(String equipmentName, String target, String preposition) {
        this.commandType = CommandType.USE;
        this.equipmentName = equipmentName;
        this.target = target;
        this.preposition = preposition + " ";
    }

    //runs the use command. If the player has the equipment it preceeds to check if the target matches the specified targt for the piece of equipment before proceeding to run the command and revealing the resultant game object. If the target as null, as would be in use antidote, then if the target specified in the use i formation is also null, then the command proceeds to unhide the resultant game object, otherwise it prints invalid use target
    public String execute(GameState gameState){
        if(gameState.getPlayer().hasEquipment(equipmentName)){
            Equipment equipment = gameState.getPlayer().getEquipment(equipmentName);
            Feature targetFeature = gameState.getMap().getCurrentRoom().getFeatureByName(target);
            Item targetItem = gameState.getPlayer().getItem(target);
            Item targetItemRoom = gameState.getMap().getCurrentRoom().getItemByName(target);
            if(targetFeature != null && targetFeature.getId().equals(equipment.getUseInformation().getTarget()) && !equipment.getUseInformation().isUsed()){
                if(gameState.getMap().getCurrentRoom().getFeature(equipment.getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getFeature(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getItem(equipment.getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getItem(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getEquipment(equipment.getUseInformation().getResult()) != null){
                            gameState.getMap().getCurrentRoom().getEquipment(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getExit(equipment.getUseInformation().getResult()) != null){
                            gameState.getMap().getCurrentRoom().getExit(equipment.getUseInformation().getResult()).setHidden(false);
                }
                equipment.getUseInformation().setUsed(true);
                gameState.getPlayer().addToScore(20);
                gameState.getPlayer().removeEquipment(equipment);
                gameState.getMap().getCurrentRoom().addEquipment(equipment);
                return equipment.getUseInformation().getAction() + "\n" + equipment.getUseInformation().getMessage();
            }else if((targetItem != null) && targetItem.getId().equals(equipment.getUseInformation().getTarget()) && !equipment.getUseInformation().isUsed()){
                if(gameState.getMap().getCurrentRoom().getFeature(equipment.getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getFeature(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getItem(equipment.getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getItem(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getEquipment(equipment.getUseInformation().getResult()) != null){
                            gameState.getMap().getCurrentRoom().getEquipment(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getExit(equipment.getUseInformation().getResult()) != null){
                            gameState.getMap().getCurrentRoom().getExit(equipment.getUseInformation().getResult()).setHidden(false);
                }
                equipment.getUseInformation().setUsed(true);
                gameState.getPlayer().addToScore(20);
                return equipment.getUseInformation().getAction() + "\n" + equipment.getUseInformation().getMessage();
            }else if((targetItemRoom != null) && targetItemRoom.getId().equals(equipment.getUseInformation().getTarget()) && !equipment.getUseInformation().isUsed()){
                if(gameState.getMap().getCurrentRoom().getFeature(equipment.getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getFeature(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getItem(equipment.getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getItem(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getEquipment(equipment.getUseInformation().getResult()) != null){
                            gameState.getMap().getCurrentRoom().getEquipment(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getExit(equipment.getUseInformation().getResult()) != null){
                            gameState.getMap().getCurrentRoom().getExit(equipment.getUseInformation().getResult()).setHidden(false);
                }
                equipment.getUseInformation().setUsed(true);
                gameState.getPlayer().addToScore(20);
                gameState.getPlayer().removeEquipment(equipment);
                gameState.getMap().getCurrentRoom().addEquipment(equipment);
                return equipment.getUseInformation().getAction() + "\n" + equipment.getUseInformation().getMessage();
            }
            else if(gameState.getPlayer().getEquipment(equipmentName).getUseInformation().isUsed()){
                gameState.getPlayer().subtractFromScore(5);
                return "You have already used " + equipmentName;
            }
            else if(equipment.getUseInformation().getTarget() == ""){
                if(gameState.getMap().getCurrentRoom().getFeature(equipment.getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getFeature(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getItem(equipment.getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getItem(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getEquipment(equipment.getUseInformation().getResult()) != null){
                            gameState.getMap().getCurrentRoom().getEquipment(equipment.getUseInformation().getResult()).setHidden(false);
                }
                if(gameState.getMap().getCurrentRoom().getExit(equipment.getUseInformation().getResult()) != null){
                            gameState.getMap().getCurrentRoom().getExit(equipment.getUseInformation().getResult()).setHidden(false);
                }
                equipment.getUseInformation().setUsed(true);
                gameState.getPlayer().addToScore(20);
                gameState.getPlayer().removeEquipment(equipment);
                gameState.getMap().getCurrentRoom().addEquipment(equipment);
                return equipment.getUseInformation().getAction() + "\n" + equipment.getUseInformation().getMessage();
            }
            else{
                return "Invalid use target";
            }
        }
        else{
            return "You do not have "+ equipmentName + " to use";
        }
    }

    @Override
    public String toString(){
        return "Use command: " + equipmentName + " " + preposition + target;
    }
}
