package org.uob.a2;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {
    private static Parser parser;
    private static Tokeniser tokeniser;
    private static ArrayList<Token> tokensList;
    private static GameState gameState;
    
    //constructor
    public Game(){
    } 
    
    //calls the setup and start method, start method conatines theloop for each turn
    public static void main(String[] args) throws CommandErrorException{
        Game game = new Game();
        game.setup();
        game.start();
    }

    //initialises everything and parses the data file
    public static void setup(){
        File file = new File("data/game.txt");
        String absolutePath = file.getAbsolutePath();
        gameState = GameStateFileParser.parse(absolutePath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a player name:");
        System.out.print(">");
        String userInput = scanner.nextLine().trim();
        if(userInput.equals("")){
            System.out.println("You did not enter a name so you are player 1");
        }else{
            gameState.getPlayer().setName(userInput);
        }
        parser = new Parser();
        tokeniser = new Tokeniser();
        tokensList = new ArrayList<Token>();
        gameState.getMap().setCurrentRoom("r1");
        gameState.getPlayer().setScore(0);
        gameState.getPlayer().setHealth(10);
        
    }

    //starting the game
    public static void start(){
        System.out.println(new Help().execute(gameState));

        System.out.println("\nYou walk up into a mansion... seemingly abandoned. The clues led here. This must be where the treasure is hidden.\n");
        
        gameState.getMap().createMap(gameState);

        System.out.println("Scoring system:");
        System.out.println("Successfully using equipment:           +20");
        System.out.println("Successfully combining items:           +10");
        System.out.println("Trying to reuse a piece of equipment:   -10");
        System.out.println("Checking the map using 'status map':    -5");
        System.out.println("Successfully collecting the treasure:   +50");
        System.out.println("Collecting other valuables:             +30");
        System.out.println("You start with a score of 0.");
        System.out.println(gameState.getPlayer().getHealth());

        Scanner scanner = new Scanner(System.in);
        
        while (gameState.getGameOver() == false){
            scoreCheck(gameState);
            if (checkGameOver(gameState)) {
                break;
            }
            System.out.print("> ");
            String userInput = scanner.nextLine().trim();
            tokeniser.tokenise(tokeniser.sanitise(userInput));
            tokensList = tokeniser.getTokens();
            try{
                Command command = parser.parse(tokensList);
                turn(command);
            }
            catch(CommandErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }

    //method called in each turn in the start method
    public static void turn(Command command){
        String result = command.execute(gameState);
        System.out.println(result);

        healthCheck(gameState);
    }

    //check for game over to break out of the game loop, either winning or sunning out of health
    public static boolean checkGameOver(GameState gameState){
        if(gameState.getPlayer().hasItem(GameStateFileParser.getWin())){
            System.out.println(gameState.getPlayer().getName() + ", you found the treasure... YOU WIN!");
            System.out.println("Score: " + gameState.getPlayer().getScore());
            System.out.println(gameState.getPlayer().getHealth());
            return true; //if game over, true returned
        }
        if(gameState.getPlayer().getHealthInt() < 1){
            System.out.println("Game Over! You ran out of health...");
            System.out.println("Score: " + gameState.getPlayer().getScore());
            return true; //if game over, true returned
        }
        else return false; //if game not over, false returned
    }

    //updates the players score based on the score items, as gotten from the file parser
    public static void scoreCheck(GameState gameState){
        for(Score scoreItem : GameStateFileParser.getScoreItems()){
            if(gameState.getPlayer().hasItem(scoreItem.getScoreItem())){ //if the player has the score item, the amount is added to thw score once, boolean check ensures it isnt added multile items
                if(!scoreItem.getScoreItemCheck()){
                    gameState.getPlayer().addToScore(scoreItem.getScoreAmount());
                    scoreItem.setScoreItemCheck(true);
                }
             }
        }
    }

    //updates the players health based on the score items, as gotten from the file parser
    public static void healthCheck(GameState gameState){
        for(Health healthItem : GameStateFileParser.getHealthItems()){ //if the player is in the room, and the condition is met, eg item not used, then every turn the health changes by the specified amount
            if(gameState.getMap().getCurrentRoom().getId().equals(healthItem.getHealthPlace()) && gameState.getMap().getCurrentRoom().getExit(healthItem.getHealthCondition()).getHidden()){
            gameState.getPlayer().changeHealth(healthItem.getHealthEffect());
            System.out.println(gameState.getPlayer().getHealth());
            }
        }
    }
}
