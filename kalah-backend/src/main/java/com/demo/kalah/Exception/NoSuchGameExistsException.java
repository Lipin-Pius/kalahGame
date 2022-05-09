package com.demo.kalah.Exception;

/**
 * Thrown if inmemory data store do not contain a game with given gameId
 */
public class NoSuchGameExistsException extends Exception{
    public NoSuchGameExistsException(final String gameID) {
        super("No Game found with id:"+gameID);
    }
}
