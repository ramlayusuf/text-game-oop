package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {

    private String name;
    private ArrayList<Item> inventory;
    private ArrayList<Equipment> equipment;
    private int score;
    private int health;


    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }

    //constructor
    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.score = score;
        this.health = health;
    }

    //returns the players health as a string of hearts or _ dependng on how much health they have, always total 10 symbols
    public String getHealth(){
        String healthOutput = "Health:";
        for(int i = 0; i<health; i++){
            healthOutput += " ❤️";
        }
        healthOutput += " ";
        for(int i = 10; i>health; i--){
            healthOutput += " _";
        }
        return healthOutput;
    }

    //rretirns players health as an integer, need for health calculations, eg decreasing health
    public int getHealthInt(){
        return health;
    }

    //set health called at start of game to set the health
    public void setHealth(int health){
        this.health = health;
    }

    //method to update the health based on the integer entered in the parameter, could be negative
    public void changeHealth(int amount){
        health+=amount;
    }
    
    //method to return the players score integere
    public int getScore(){
        return score;
    }

    //usedto set intial score at the start of the game
    public void setScore(int x){
        this.score = x;
    }

    //updates score by adding a specified int
    public void addToScore(int add){
        score+=add;
    }

    //updates score by subtracting a specified int
    public void subtractFromScore(int subtract){
        score-=subtract;
    }

    //getter setter methods
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    //method to an item to inventory array list
    public void addItem(Item item){
        inventory.add(item);
    }

    //method to check i f a player has na item based on the item name
    public boolean hasItem(String itemName){
        for(Item item : inventory){
            if(item.getName().equalsIgnoreCase(itemName)){
                return true;
            }
        }
        return false;
    }

    public Item getItem(String itemName){
        for(Item item : inventory){
            if(item.getName().equalsIgnoreCase(itemName)){
                return item;
            }
        }
        return null;
    }

    public void removeItem(Item item){
        this.inventory.remove(item);
    }

    public void addEquipment(Equipment equipment){
        this.equipment.add(equipment);
    }

    public boolean hasEquipment(String equipmentName){
        for(Equipment equipment : equipment){
            if(equipment.getName().equalsIgnoreCase(equipmentName)){
                return true;
            }
        }
        return false;
    }

    public Equipment getEquipment(String equipmentName) {
        for(Equipment equipment : equipment){
            if(equipment.getName().equalsIgnoreCase(equipmentName)){
                return equipment;
            }
        }
        return null;
    }

    public void removeEquipment(Equipment equipment){
        this.equipment.remove(equipment);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }
}
