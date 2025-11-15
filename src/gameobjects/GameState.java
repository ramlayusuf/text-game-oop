package org.uob.a2.gameobjects;

/**
 * Represents the current state of the game, including the map and the player.
 * 
 * <p>
 * The game state contains all necessary information about the game's progress, such as
 * the player's status and the state of the game map.
 * </p>
 */
public class GameState {

    private Map map;
    private Player player;
    private boolean gameOver;

    //constructor methods
    public GameState(){
        this.map = null;
        this.player = null;
        this.gameOver = false;
    }
    
    public GameState(Map map, Player player){
        this.map = map;
        this.player = player;
        this.gameOver = false;
    }

    //setter and getter methods
    public Map getMap(){
        return map;
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    public boolean getGameOver(){
        return gameOver;
    }

    public Player getPlayer(){
        return player;
    }

    public void setMap(Map map){
        this.map = map;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
    /**
     * Returns a string representation of the game state, including the map and player details.
     *
     * @return a string describing the game state
     */
    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}
