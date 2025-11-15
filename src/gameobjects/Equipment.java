package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {

    protected UseInformation useInformation;

    //constructor method
    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation){
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }

    //getter and setter methods for use information
    public UseInformation getUseInformation(){
        return useInformation;
    }
    
    public void setUseInformation(UseInformation useInformation){
        this.useInformation = useInformation;
    }
    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }

    //when equipment is used, the result id set to not hidden
    public String use(GameObject target, GameState gameState){
        if(gameState.getMap().getCurrentRoom().getFeature(getUseInformation().getResult()) != null){
            gameState.getMap().getCurrentRoom().getFeature(getUseInformation().getResult()).setHidden(false);
        }
        if(gameState.getMap().getCurrentRoom().getItem(getUseInformation().getResult()) != null){
            gameState.getMap().getCurrentRoom().getItem(getUseInformation().getResult()).setHidden(false);
        }
        if(gameState.getMap().getCurrentRoom().getEquipment(getUseInformation().getResult()) != null){
                    gameState.getMap().getCurrentRoom().getEquipment(getUseInformation().getResult()).setHidden(false);
        }
        return "Using " + getName() + ": " + useInformation;
    }
}
