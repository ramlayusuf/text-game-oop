package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.uob.a2.gameobjects.*;


/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {

    private static ArrayList<Health> healthItems = new ArrayList<>();
    private static ArrayList<Score> scoreItems = new ArrayList<>();
    private static String win = "";

    //constructor
    public GameStateFileParser(){
    }

    //method to arse the game.txt file and create a gamestate by adding rooms and items and equipment etc.
    public static GameState parse(String filename){
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Equipment> equipment = new ArrayList<>();
        ArrayList<Feature> features = new ArrayList<>();
        ArrayList<Container> containers = new ArrayList<>();
        ArrayList<Exit> exits = new ArrayList<>();
        Player player = null;
        String mapId = null;

        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            Room currentRoom = null;
    
            while ((line = br.readLine()) != null) {
                line = line.trim();
    
                if (line.isEmpty()) continue;
    
                String[] parts = line.split(",", -1);
    
                //things added based on the word at the start of the line, variables separated by ,
                if(line.startsWith("score:")){
                    //parse score info, determine th eitems the affect the players score
                    String scoreItem = parts[0].split(":")[1].trim();
                    int scoreAmount = Integer.parseInt(parts[1].trim());
                    boolean scoreItemCheck = Boolean.parseBoolean(parts[2].trim());
                    scoreItems.add(new Score(scoreItem, scoreAmount, scoreItemCheck));
                }
                if(line.startsWith("health:")){
                    //parse health info, determines the conditions that affcet the players health
                    String healthPlace = parts[0].split(":")[1].trim();
                    String healthCondition = parts[1].trim();
                    int healthEffect = Integer.parseInt(parts[2].trim());
                    healthItems.add(new Health(healthPlace, healthCondition, healthEffect));
                }
                if(line.startsWith("win:")){
                    //parse win info, detrmins the item the player should get to win
                    String winItem = parts[0].split(":")[1].trim();
                    win = winItem;
                }
                if (line.startsWith("player:")) {
                    //parse player info
                    player = new Player(parts[0].split(":")[1].trim());
                } else if (line.startsWith("map:")) {
                    mapId = parts[0].split(":")[1].trim();
                } else if (line.startsWith("room:")) {
                    String roomId = parts[0].split(":")[1].trim();
                    String roomName = parts[1].trim();
                    String roomDescription = parts[2].trim();
                    boolean isHidden = Boolean.parseBoolean(parts[3].trim());
                    currentRoom = new Room(roomId, roomName, roomDescription, isHidden);
                    rooms.add(currentRoom);
                } else if (line.startsWith("equipment:")) {
                    //parse equipment info
                    String equipId = parts[0].split(":")[1].trim();
                    String equipName = parts[1].trim();
                    String equipDescription = parts[2].trim();
                    boolean equipHidden = Boolean.parseBoolean(parts[3].trim());
                    String useAction = parts[4].trim();
                    String useTarget = parts[5].trim();
                    String useResult = parts[6].trim();
                    String useDescription = parts[7].trim();
                    UseInformation useInfo = new UseInformation(false, useAction, useTarget, useResult, useDescription);
                    Equipment equip = new Equipment(equipId, equipName, equipDescription, equipHidden, useInfo);
                    currentRoom.addEquipment(equip);
                } else if (line.startsWith("container:")) {
                    //parse conatiner info
                    String containerId = parts[0].split(":")[1].trim();
                    String containerName = parts[1].trim();
                    String containerDescription = parts[2].trim();
                    boolean containerHidden = Boolean.parseBoolean(parts[3].trim());
                    Container container = new Container(containerId, containerName, containerDescription, containerHidden);
                    currentRoom.addFeature(container); 
                } else if (line.startsWith("item:")) {
                    // Parse item info
                    String itemId = parts[0].split(":")[1].trim();
                    String itemName = parts[1].trim();
                    String itemDescription = parts[2].trim();
                    boolean itemHidden = Boolean.parseBoolean(parts[3].trim());

                    if(parts.length == 4){
                        //parse item info fo more parametrs for combinale items
                        Item item = new Item(itemId, itemName, itemDescription, itemHidden);
                        currentRoom.addItem(item);
                    }
                    
                    if(parts.length > 4){
                        boolean itemCombinable = Boolean.parseBoolean(parts[4].trim());
                        String combinationResultEquipment = parts[5].trim();
                        String equipDescription = parts[6].trim();
                        String equipAction = parts[7].trim();
                        String equipTarget = parts[8].trim();
                        String equipResult = parts[9].trim();
                        String equipMessage = parts[10].trim();
                        Item item = new Item(itemId, itemName, itemDescription, itemHidden, itemCombinable, combinationResultEquipment, equipDescription, equipAction, equipTarget, equipResult, equipMessage);
                        currentRoom.addItem(item);
                    }
                }else if(line.startsWith("exit:")){
                    //parse exit info
                    String exitId = parts[0].split(":")[1].trim();
                    String exitName = parts[1].trim();
                    String exitDescription = parts[2].trim();
                    String nextRoomId = parts[3].trim();
                    boolean exitHidden = Boolean.parseBoolean(parts[4].trim());
                    Exit exit = new Exit(exitId, exitName, exitDescription, nextRoomId, exitHidden);
                    currentRoom.addExit(exit);
                }else if (line.startsWith("feature:")){
                    if (currentRoom != null) {
                        //parse feature info
                        String featureId = parts[0].split(":")[1].trim();
                        String featureName = parts[1].trim();
                        String featureDescription = parts[2].trim();
                        boolean featureHidden = Boolean.parseBoolean(parts[3].trim());
                        Feature feature = new Feature(featureId, featureName, featureDescription, featureHidden);
                        currentRoom.addFeature(feature);
                    }
                }
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        Map gameMap = new Map();
        gameMap.setCurrentRoom(mapId);
        for(Room room : rooms){
            gameMap.addRoom(room); //add rooms to map
        }
        Room currentRoom = gameMap.getCurrentRoom(); //ietms, equipment, exits etc. addded for each room
        if(currentRoom != null){
            for(Item item : items){
                currentRoom.addItem(item);
            }
            for(Equipment equip : equipment){
                currentRoom.addEquipment(equip);
            }
            for(Container container : containers){
                currentRoom.addFeature(container);
            }
            for(Exit exit : exits){
                currentRoom.addExit(exit);
            }
        }else{
            System.out.println("Current room '" + mapId + "' not found in rooms list.");
        }

        GameState gameState = new GameState(gameMap, player);

        return gameState;
    }

    //returns healthItems and score items array list, and winning item to be considred in the game mthod
    public static ArrayList<Health> getHealthItems(){
        return healthItems;
    }

    public static ArrayList<Score> getScoreItems(){
        return scoreItems;
    }

    public static String getWin(){
        return win;
    }
}
   
