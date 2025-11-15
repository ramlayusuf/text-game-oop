package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {

    private ArrayList<Room> rooms;
    private String currentRoomId;
    private int width;
    private int height;
    private char[][] map;
    final private char EMPTY = '.';
    private String mapDisplay;

    //constructor
    public Map(){
        this.rooms = new ArrayList<>();
        this.currentRoomId = null;
        this.mapDisplay = mapDisplay;
    }

    //getter and setter methods to return the room or list of rooms, and to set the current room, all based on the id
    
    public Room getCurrentRoom(){
        for(Room room : rooms){
            if(room.getId().equals(currentRoomId)){
                return room;
            }
        }
        return null;
    }

    public Room getRoomById(String id){
        for(Room room : rooms){
            if(room.getId().equals(id)){
                return room;
            }
        }
        return null;
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void setCurrentRoom(String roomId){
        this.currentRoomId = roomId;
    }

    //methodto create the map. Called at the start of the game tocreate the map. Not harcoded, dynamically creates the map based on the data text file, reads the rooms from the gameSTate and adds them to the map acordingly. The symbol for each room is the first letter of the room name. Also prints a compass at the start to help with moving in different directions
    public void createMap(GameState gameState){
        System.out.println("Compass:");
        System.out.println("   N   ");
        System.out.println("W     E");
        System.out.println("   S   ");
        System.out.println("\n");
        
        int width = calculateMapWidth(); //width of map is set basd on the mathod called, explained below
        int height = calculateMapHeight(); //height of map is set basd on the mathod called, explained below
        
        HashMap<String, int[]> roomPositions = new HashMap<>();

        int startX = 0;
        int startY = 0;

        roomPositions.put(gameState.getMap().getCurrentRoom().getId(), new int[] {0, 0}); //first room si sput on map at the top left corner and the rest added accordingly based on exits

        map = new char[height][width];

        for(int i = 0; i< height; i++){
            for(int j = 0; j< width; j++){
                map[i][j] = EMPTY;
            }
        }
        
        for (Room room : rooms) {
            int[] currentPosition = roomPositions.get(room.getId());
            if (currentPosition == null) continue;
            int x = currentPosition[0];
            int y = currentPosition[1];

            map[y][x] = room.getName().charAt(0);

            for (Exit exit : room.getExits()) {
                Room nextRoom = getRoomById(exit.getNextRoom());
                if (nextRoom == null || roomPositions.containsKey(nextRoom.getId())) {
                    continue;
                }
                int nextX = x; //exits to the east/west change the next room position by 1/-1, so the next room is placed accordingly
                int nextY = y; //exits to the north/south change the next room position by 1/-1, so the next room is placed accordingly

                if (exit.getName().equalsIgnoreCase("east")) {
                    nextX += 1;
                } else if (exit.getName().equalsIgnoreCase("south")) {
                    nextY += 1;
                }else if (exit.getName().equalsIgnoreCase("west")) {
                    nextX -= 1;
                }else if (exit.getName().equalsIgnoreCase("north")) {
                    nextY -= 1;
                }

                roomPositions.put(nextRoom.getId(), new int[] {nextX, nextY});
            }
        }
        mapDisplay = "";
        for (int i=0; i < height; i++){
            for(int j = 0; j < width; j++){
                mapDisplay += map[i][j] + " ";
            }
            mapDisplay = mapDisplay + '\n';
        }
    }

    public String display(GameState gameState){ //methiod to display the created map in the previous method
        return mapDisplay + "\nYou are at " + gameState.getMap().getCurrentRoom().getName();
    }

    //method to calculate the width needed for the map
    public int calculateMapWidth() {
    int width = 0;

    HashMap<String, int[]> roomPositions = new HashMap<>();
    roomPositions.put(getCurrentRoom().getId(), new int[] {0, 0});

    for (Room room : rooms) {
        int[] position = roomPositions.get(room.getId());
        if (position == null) continue;
        int x = position[0];

        width = Math.max(width, x + 1);

        for (Exit exit : room.getExits()) {
            Room nextRoom = getRoomById(exit.getNextRoom());
            if (nextRoom == null || roomPositions.containsKey(nextRoom.getId())) {
                continue;
            }

            int nextX = x; //new rooms width position calculated based on exits
            if (exit.getName().equalsIgnoreCase("east")) {
                nextX += 1;
            } else if (exit.getName().equalsIgnoreCase("west")) {
                nextX -= 1;
            }

            roomPositions.put(nextRoom.getId(), new int[] {nextX, 0}); // Store new room's position
            width = Math.max(width, nextX + 1); //maximum height needed is determined based on if the new room is further than the previous
        }
    }
    return width;
    }

    //method to calculate the height needed for the map
    public int calculateMapHeight() {
    int height = 0;

    HashMap<String, int[]> roomPositions = new HashMap<>();
    roomPositions.put(getCurrentRoom().getId(), new int[] {0, 0});

    for (Room room : rooms) {
        int[] position = roomPositions.get(room.getId());
        if (position == null) continue;
        int y = position[1];

        height = y + 1;

        for (Exit exit : room.getExits()) {
            Room nextRoom = getRoomById(exit.getNextRoom());
            if (nextRoom == null || roomPositions.containsKey(nextRoom.getId())) {
                continue;
            }

            int nextY = y; //new rooms height calculated based on exits
            if (exit.getName().equalsIgnoreCase("south")) {
                nextY += 1;
            } else if (exit.getName().equalsIgnoreCase("north")) {
                nextY -= 1;
            }

            roomPositions.put(nextRoom.getId(), new int[] {0, nextY});
            height = Math.max(height, nextY + 1); //maximum height needed is determined based on if the new room is higher than the previous
        }
    }
    return height;
    }


    
    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

