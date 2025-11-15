package org.uob.a2.gameobjects;

/**
 * Represents an item in the game, which is a type of {@code GameObject}.
 *
 * <p>
 * Items can be collected, used, or interacted with by the player.
 * This class inherits common properties from {@code GameObject}.
 * </p>
 */
public class Item extends GameObject {

    private boolean combinable;
    private String combinationResultEquipment;
    private String equipAction;
    private String equipTarget;
    private String equipResult;
    private String equipMessage;
    private String equipDescription;
    
    //two constructors, one for combinable items, and oe for just items where the combinable is set to false and all combinable variables are null.
    
    public Item(String id, String name, String description, boolean hidden){
        super(id, name, description, hidden);
        this.combinable = false;
        this.combinationResultEquipment = null;
        this.equipAction = null;
        this.equipTarget = null;
        this.equipResult = null;
        this.equipMessage = null;
        this.equipDescription = null;
    }

    public Item(String id, String name, String description, boolean hidden, boolean combinable, String combinationResultEquipment, String equipDescription, String equipAction, String equipTarget, String equipResult, String equipMessage){
        super(id, name, description, hidden);
        this.combinable = combinable;
        this.combinationResultEquipment = combinationResultEquipment;
        this.equipAction = equipAction;
        this.equipTarget = equipTarget;
        this.equipResult = equipResult;
        this.equipMessage = equipMessage;
        this.equipDescription = equipDescription;
    }

    //returns whether the item is combinable or not
    public boolean getCombinable(){
        return combinable;
    }

    //returns the result of the combination, ie the equipment formed by the combination as new equipment
    public Equipment getCombinationResult(){
        UseInformation useInfo = new UseInformation(false,equipAction,equipTarget,equipResult,equipMessage);
        return new Equipment("comb" + combinationResultEquipment,combinationResultEquipment,equipDescription,false,useInfo);
    }
    
     /**
     * Returns a string representation of the item by calling the superclass's {@code toString} method.
     *
     * @return a string describing the item
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
