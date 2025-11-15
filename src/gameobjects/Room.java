package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {

    private ArrayList<Item> items;
    private ArrayList<Equipment> equipment;
    private ArrayList<Feature> features;
    private ArrayList<Exit> exits;

    public Room(String id, String name, String description, boolean hidden){
        super(id, name, description, hidden);
        this.items = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.features = new ArrayList<>();
        this.exits = new ArrayList<>();
    }
    
    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public void removeEquipment(Equipment equipment){
        this.equipment.remove(equipment);
    }

    public void addEquipment(Equipment equipment){
        this.equipment.add(equipment);
    }

    public void addFeature(Feature feature){
        this.features.add(feature);
    }

    public void addExit(Exit exit){
        this.exits.add(exit);
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public ArrayList<Feature> getFeatures(){
        return features;
    }

    public ArrayList<Exit> getExits(){
        return exits;
    }

    public ArrayList<Equipment> getEquipments(){
        return equipment;
    }

    public Exit getExit(String id){
        for (Exit exit : exits){
            if(exit.getId().equals(id)){
                return exit;
            }
        }
        return null;
    }

    public Exit getExitByDirection(String name){
        for(Exit exit : exits){
            if(exit.getName().equalsIgnoreCase(name)){
                return exit;
            }
        }
        return null;
    }

    public Item getItem(String id){
        for(Item item : items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }

    public Item getItemByName(String name){
        for(Item item : items){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }

    public Feature getFeatureByName(String name) {
        for(Feature feature : features){
            if(feature.getName().equalsIgnoreCase(name)){
                return feature;
            }
        }
        return null;
    }

    public Equipment getEquipmentByName(String name){
        for(Equipment equipment : equipment){
            if(equipment.getName().equalsIgnoreCase(name)){
                return equipment;
            }
        }
        return null;
    }

    public Equipment getEquipment(String id){
        for(Equipment equipment : equipment){
            if(equipment.getId().equals(id)){
                return equipment;
            }
        }
        return null;
    }

    public Feature getFeature(String id){
        for(Feature feature : features){
            if(feature.getId().equals(id)){
                return feature;
            }
        }
        return null;
    }

    public ArrayList<GameObject> getAll() {
        ArrayList<GameObject> allObjects = new ArrayList<>();
        allObjects.addAll(items);
        allObjects.addAll(equipment);
        allObjects.addAll(exits);
        allObjects.addAll(features);
        return allObjects;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getDescription(){
        return description;
    }

    public boolean hasItem(String itemName){
        if(getItemByName(itemName) != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean hasEquipment(String name){
        if(getEquipmentByName(name) != null){
            return true;
        }
        else{
            return false;
        }
    }

    
    /**
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */
    @Override
    public String toString() {
        String out = "[" + id + "] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e + "\n";
        }
        for (Feature f : this.features) {
            out += f + "\n";
        }
        for (Exit e : this.exits) {
            out += e + "\n";
        }
        return out + "\n";
    }
}
