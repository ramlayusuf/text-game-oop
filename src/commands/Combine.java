package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

public class Combine extends Command {

    private String item1;
    private String item2;
    private String preposition;
    
    //constructor method
    public Combine(String item1, String item2, String preposition) {
        this.commandType = CommandType.COMBINE;
        this.item1 = item1;
        this.item2 = item2;
        this.preposition = preposition;
    }

    //execute runsthe command, combines the two items into one new equipment which is added to the players inventoy
    public String execute(GameState gameState){
        if(gameState.getPlayer().hasItem(item1)){
            if(gameState.getPlayer().hasItem(item2)){
                if(gameState.getPlayer().getItem(item1).getCombinable() == true){
                    if(gameState.getPlayer().getItem(item2).getCombinable() == true){ //if player has both items and both items are combinable
                        Equipment equipment = gameState.getPlayer().getItem(item1).getCombinationResult();
                        gameState.getPlayer().removeItem(gameState.getPlayer().getItem(item1));
                        gameState.getPlayer().removeItem(gameState.getPlayer().getItem(item2));
                        gameState.getPlayer().addEquipment(equipment);
                        gameState.getPlayer().addToScore(10);
                        return item1 + " and " + item2 + " combined and " + equipment.getName() + " added to equipment.";//the items are combined and the combination result is added to inventory, and individual items are removed
                    }
                    else{
                        return "Cannot combine items as " + item2 + " is not combinable.";
                    }
                }
                else{
                    return "Cannot combine items as " + item1 + " is not combinable.";
                }
            }
            else{
                return "You do not have "+ item2 + " to combine";
            }
        }
        else{
            return "You do not have "+ item1 + " to combine";
        }
    }

    @Override
    public String toString(){
        return "Combine command: " + item1 + " " + preposition + " " + item2;
    }
}
