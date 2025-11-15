package org.uob.a2.parser;

/**
 * Represents a token in the parsing process, consisting of a {@code TokenType} and an optional value.
 * 
 * <p>
 * Tokens are used to represent the smallest units of meaning in the command input,
 * such as keywords, or variables.
 * </p>
 */
public class Token {

    private TokenType tokenType;
    private String value;

    //constructors, two depending on whether the specific command needs a topic or not
    
    public Token(TokenType tokenType, String value){
        this.tokenType = tokenType;
        this.value = value;
    }
    
    public Token(TokenType tokenType){
        this.tokenType = tokenType;
        this.value = null;
    }

    //getter methods
    
    public TokenType getTokenType(){
        return tokenType;
    }
    
    public String getValue(){
        return value;
    }
}
