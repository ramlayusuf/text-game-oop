package org.uob.a2.gameobjects;

public class Health{
    
    private String healthPlace;
    private String healthCondition;
    private int healthEffect;
    
    //constructor
    public Health(String healthPlace, String healthCondition, int healthEffect){
        this.healthPlace = healthPlace;
        this.healthCondition = healthCondition;
        this.healthEffect = healthEffect;
    }

    //getter methods returning th information needed for the health items: where they apply, what is the condition under which they apply, and how do the affect the health numerically
    
    public String getHealthPlace(){
        return healthPlace;
    }

    public String getHealthCondition(){
        return healthCondition;
    }

    public int getHealthEffect(){
        return healthEffect;
    }

}