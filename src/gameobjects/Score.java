package org.uob.a2.gameobjects;

public class Score{
    
    private String scoreItem;
    private int scoreAmount;
    private boolean scoreItemCheck;
    
    //constructor
    public Score(String scoreItem, int scoreAmount, boolean scoreItemCheck){
        this.scoreItem = scoreItem;
        this.scoreAmount = scoreAmount;
        this.scoreItemCheck = scoreItemCheck;
    }

    //setter getter methods

    //items which collecting them changes the score
    public String getScoreItem(){
        return scoreItem;
    }

    //the amountb they change the score by
    public int getScoreAmount(){
        return scoreAmount;
    }

    //whether they have yet updated the score
    public boolean getScoreItemCheck(){
        return scoreItemCheck;
    }

    public void setScoreItemCheck(boolean set){
        this.scoreItemCheck = set;
    }

}