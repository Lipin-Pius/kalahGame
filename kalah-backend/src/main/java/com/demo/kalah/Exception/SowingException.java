package com.demo.kalah.Exception;

/**
 * Thrown when a given move/sow operation fails
 */
public class SowingException extends Exception{
    public SowingException(final String gameAndMove) {
        super("Sowing Exception while performing "+gameAndMove);
    }
}
