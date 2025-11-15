package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {

    private String topic;
  
    public Help(String topic) {
        this.topic = topic;
        this.commandType = CommandType.HELP;
    }

    public Help() {
        this.topic = null;
        this.commandType = CommandType.HELP;
    }

    public String execute(GameState gameState){
        if(topic == null){
            if(gameState != null && gameState.getPlayer() != null){
                return gameState.getPlayer().getName() + ", Welcome to the game!\n" +
                   "Use the following commands to work through the game:\n" +
                   "- GET *item*\n" +
                   "- DROP *item*\n" +
                   "- MOVE *direction*\n" +
                   "- LOOK *object*\n" +
                   "- USE *equipment* on *feature*\n" +
                   "- STATUS *player/score/map/inventory*\n" +
                   "- COMBINE *item* and *item*\n" +
                   "- QUIT - to leave the game\n" +
                   "- HELP - to view this message again\n" +
                   "- HELP *topic* - for more specific help on a topic";
            }
            else{
                return "Welcome to the game!\n" +
                   "Use the following commands to work through the game:\n" +
                   "- GET *item*\n" +
                   "- DROP *item*\n" +
                   "- MOVE *direction*\n" +
                   "- LOOK *object*\n" +
                   "- USE *equipment* on *feature*\n" +
                   "- STATUS *player/score/map/inventory*\n" +
                   "- COMBINE *item* and *item*\n" +
                   "- QUIT - to leave the game\n" +
                   "- HELP - to view this message again\n" +
                   "- HELP *topic* - for more specific help on a topic";
            }
        }
        else{
            switch (topic.toLowerCase()){
                case "commands":
                    return "Commands:\nLook *room/exits/features/item/equipment/feature* - To view a description of any game objects, or look at the available exits, features, items and equipment in your current room or inventory\nMove *north/east/south/west* (You can only move between rooms through exits)\nDrop *item/equipment* - to remove it from your inventory and drop it in the room you are in\nGet *item/equipment* - to pick an item up from a room you are in and add it to your inventory\nUse *equipment* on *target* (or for some, just 'use *equipment*') - to use a piece of equipment from your inventory\nStatus *player* - to view your player name, health, and score\nStatus *inventory* - to view your current inventory\nStatus *map* - to view the map\nStatus *score/health* - to see your current score/health\nStatus *item/equipment* to view a description of an item or piece of equipment in your inventory\nCombine *item* and *item* to combine two items into a pice of equipment or item\nquit - to leave the game";
                case "equipment":
                    return "equipment includes items you can 'equip' to upgrade your abilities in the game";
                case "items":
                    return "items can be interacted with and used un certain places to solve 'puzzles'";
                case "inventory":
                    return "All the items and pieces of equipment a player currently has";
                case "move":
                    return "MOVE Command: Use the 'move' command to move in a given direction.\nMove *north/east/south/west* (You can only move between rooms through exits)";
                case "get":
                    return "GET Command: Use the 'get' command to pick up an item or piece of equipment from the room you are in and add it to your inventory.\nGet *item/equipment*";
                case "drop":
                    return "DROP Command: Use the 'drop' command to drop an item or piece of equipment in the room you are in and remove it from your inventory.\nDrop *item/equipment*";
                case "look":
                    return "LOOK Command: Use the 'look' command as follows:\nLook *room/exits/features/item/equipment/feature* - To view a description of any game objects, or look at the available exits, features, items and equipment in your current room or inventory";
                case "status":
                    return "STATUS Command: Use the 'status' command as follows:\nStatus *player* - to view your player name, health, and score\nStatus *inventory* - to view your current inventory\nStatus *map* - to view the map\nStatus *score/health* - to see your current score/health\nStatus *item/equipment* to view a description of an item or piece of equipment in your inventory";
                case "use":
                    return "USE Command: Use the 'use' command as follows:\nUse *equipment* on *target* (or for some, just 'use *equipment*') - to use a piece of equipment from your inventory";
                case "combine":
                    return "COMBINE Command: Use the combine command as follows:\nCombine *item* and *item* to combine two items into a piece of equipment or item";
                default:
                    return "No help available for the topic: " + topic + ".\nTry commands, equipment, items, inventory, or a specific command";
            }
        }
    }

    @Override
    public String toString() {
        return "HELP Command: " + topic;
    }
}
