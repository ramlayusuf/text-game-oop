package org.uob.a2.gameobjects;
import java.util.ArrayList;

/**
 * Represents a conatiner in the game, which is a type of feature that may contain items
 * or serve as an interactable object within a room.
 * 
 * <p>
 * Conatiner can have a name, description, and visibility state, which determines if they
 * are initially hidden or visible to the player.
 * </p>
 */
public class Container extends Feature {

    private ArrayList<GameObject> containerItems;
    
    //constructor
    public Container(String id, String name, String description, boolean hidden){
        super(id, name, description, hidden);
        this.containerItems = new ArrayList<>();
    }

    @Override
    public String getName(){
        return name;
    }

    //method to add items to the conatiner array list
    public void addItemToContainer(GameObject item) {
        containerItems.add(item);
    }

    //method to remove items frok  the container array list
    public void removeItemFromContainer(GameObject item) {
        if (containerItems.contains(item)) {
            containerItems.remove(item);
        }
        else{
            System.out.println(item + " not found in the container.");
        }
    }

    //method to return the conatiner array list
    public ArrayList<GameObject> getContainerItems() {
        return containerItems;
    }
    
    /**
     * Returns a string representation of the container.
     *
     * @return a string containing the container's id, name, description, and hidden status
     */
    @Override
    public String toString() {
        return "Container {" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", hidden=" + getHidden() +
                '}';
    
    }
 
}
