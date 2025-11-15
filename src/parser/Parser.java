package org.uob.a2.parser;

import java.util.ArrayList;

import org.uob.a2.commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {

    //constructor
    public Parser(){
        
    }

    //method to parse the tokens and return commands
    public Command parse(ArrayList<Token> tokens) throws CommandErrorException{
        if (tokens == null || tokens.size() == 0) { //if nothing entered
            throw new CommandErrorException("No tokens to parse.");
        }

        Token firstToken = tokens.get(0); //first word entered
        TokenType commandType = firstToken.getTokenType(); //type of that first word

        switch (commandType) { //different return commands basd on what the first word command was
            case COMBINE:
                if(tokens.size() < 5){ //if not specified everything
                    throw new CommandErrorException("Invalid 'combine' command. Need two items.");
                }
                String item1Name = tokens.get(1).getValue();
                Token combinePrepositionToken = tokens.get(2);
                String item2Name = tokens.get(3).getValue();
                String combinePreposition = combinePrepositionToken.getValue();
                return new Combine(item1Name,item2Name,combinePreposition);
                
            case USE:
                if(tokens.size() != 3 && tokens.size() < 4){
                     throw new CommandErrorException("Invalid 'use' command. Missing equipment or target.");
                }
                if(tokens.size()>3){
                    String equipmentName = tokens.get(1).getValue();
                    Token prepositionToken = tokens.get(2);
                    String useTarget = tokens.get(3).getValue();
                    String preposition = prepositionToken.getValue();
                    return new Use(equipmentName,useTarget,preposition);
                }
                if(tokens.size() == 3){
                    String equipmentName = tokens.get(1).getValue();
                    return new Use(equipmentName,"");
                }
                
                
            case LOOK:
                if(tokens.size() < 2){
                    throw new CommandErrorException("Invalid 'look' command. Missing target.");
                }
                String lookTarget = tokens.get(1).getValue();
                return new Look(lookTarget);
            case MOVE:
                if(tokens.size() < 2){
                throw new CommandErrorException("Invalid 'move' command. Missing direction.");
                }
                String direction = tokens.get(1).getValue();
                return new Move(direction);
            case GET:
                if(tokens.size() < 2){
                    throw new CommandErrorException("Invalid 'get' command. Missing item.");
                }
                String getItem = tokens.get(1).getValue();
                return new Get(getItem);
            case DROP:
                if (tokens.size() < 2) {
                    throw new CommandErrorException("Invalid 'drop' command. Missing item.");
                }
                String item = tokens.get(1).getValue();
                return new Drop(item);
            case STATUS:
                if (tokens.size() < 2) {
                    throw new CommandErrorException("Invalid 'status' command. Missing status topic.");
                }
                String topic = tokens.get(1).getValue();
                return new Status(topic);
            case HELP:
                if (tokens.size() == 1) {
                    return new Help();
                }
                else{
                    String helpTopic = tokens.get(1).getValue();
                    return new Help(helpTopic);
                }
            case QUIT:
                return new Quit();
            default:
                throw new CommandErrorException("Invalid command: " + firstToken.getValue());
        }
    }
}
