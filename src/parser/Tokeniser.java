package org.uob.a2.parser;

import java.util.ArrayList;

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser {
    private ArrayList<Token> tokens;

    private static final String[] KEYWORDS = {"move", "look", "get", "drop", "use", "status", "quit", "help","combine"}; //commands
    private static final String[] PREPOSITIONS = {"on", "with", "using", "at", "to"}; //prepositions

    //constrctor
    public Tokeniser(){
        this.tokens = new ArrayList<>();
    }
    
    //removes leading and trailing spaces and sets all characters in the string to lower case
    public String sanitise(String s) {
        return s.trim().toLowerCase();
    }

    //getter to get tokens array list
    public ArrayList<Token> getTokens(){
        return tokens;
    }
    
    //method to tokenise the string input in the paramter
    public void tokenise(String s) {
        tokens.clear();
        String sanitizedInput = sanitise(s);
        String[] words = sanitizedInput.split("\\s+");
        for (String word : words){
            TokenType tokenType = TokenType.ERROR; //sets to error before uodating the token type
            for(String keyword : KEYWORDS){
                if (keyword.equals(word)){
                    tokenType = TokenType.valueOf(keyword.toUpperCase());
                    break;
                }
            }
            if(tokenType == TokenType.ERROR){
                for(String preposition : PREPOSITIONS){
                    if(preposition.equals(word)){
                        tokenType = TokenType.PREPOSITION;
                        break;
                    }
                }
            }
            if(tokenType == TokenType.ERROR){
                tokenType = TokenType.VAR;
            }
            tokens.add(new Token(tokenType, word));
        }
        tokens.add(new Token(TokenType.EOL)); //new line token added at the end of the string
    }
}
