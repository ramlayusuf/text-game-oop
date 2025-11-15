package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {

    private String direction;
    
    //constructor
    public Move(String direction) {
        this.direction = direction;
        this.commandType = CommandType.MOVE;
    }

   //if the exit is visible, sets the current room to the next room speicified by the exit, so basically moves th user to the next room
    public String execute(GameState gameState){
        if(gameState.getMap().getCurrentRoom().getExitByDirection(direction) != null && !gameState.getMap().getCurrentRoom().getExitByDirection(direction).getHidden()){
            gameState.getMap().setCurrentRoom(gameState.getMap().getCurrentRoom().getExitByDirection(direction).getNextRoom());
            return "Moving towards " + direction + "\n";
        }
        else{
            return "No exit found in that direction.";
        }
    }
    
    @Override
    public String toString(){
        return "Move command: " + direction;
    }
}
