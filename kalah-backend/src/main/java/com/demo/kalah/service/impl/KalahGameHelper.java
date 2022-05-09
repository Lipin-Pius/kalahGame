package com.demo.kalah.service.impl;

import com.demo.kalah.constants.Player;
import com.demo.kalah.constants.Status;
import com.demo.kalah.dto.models.Game;
import com.demo.kalah.dto.models.Turn;

import java.util.Arrays;

/**
 * Contains utility methods, including the system house selection logic
 */
public class KalahGameHelper {
    public static Integer getLastHouseIndex(Turn turn, Game game) {
        Integer houseIndex = null;
        if (Player.USER.equals(turn.getCurrentPlayer())) {
            // return the last House index of selected by the player
            houseIndex = turn.getLastHouseIndex();
        } else {
            // logic to select a house index by the system
            // you can choose one at random
            // or implement some heuristic searching algorithm

            // for now system will choose the house with maximum no.of seeds

            houseIndex = Player.USER.houseIndex() + 1 + getHouseIndexOfMaxSeeds(Arrays.copyOfRange(game.getKalahBoard().board, Player.USER.houseIndex() + 1, Player.SYSTEM.houseIndex().intValue()));
        }
        return houseIndex;
    }

    private static Integer getHouseIndexOfMaxSeeds(Integer[] houses) {
        if (houses == null || houses.length == 0) return -1; // null or empty

        int largest = 0;
        for (int i = 1; i < houses.length; i++) {
            if (houses[i] > houses[largest]) largest = i;
        }
        return largest;
    }

    public static Integer getSeedCountByHouseIndex(Integer houseIndex, Game game) {
        return game.getKalahBoard().board[houseIndex];
    }

    public static boolean isHouseOwnedByThePlayer(int houseIndex, Player currentPlayer) {
        return Player.SYSTEM.equals(currentPlayer) ? houseIndex >= Player.USER.houseIndex() : houseIndex <= Player.USER.houseIndex();
    }

    public static boolean canPlayerSteal(int houseIndex, Game game) {
        return game.getKalahBoard().board[(Player.SYSTEM.houseIndex() - (houseIndex + 1))] > 0 && game.getKalahBoard().board[houseIndex] == 1;
    }

    public static void stealSeedsFrom(int houseIndex, Player opponent, Game game) {
        game.getKalahBoard().board[(Player.SYSTEM.houseIndex() - (houseIndex + 1))] = game.getKalahBoard().board[(Player.SYSTEM.houseIndex() - (houseIndex + 1))];
    }

    public static boolean isHousesOwnedByThePlayerEmpty(int houseIndex, Player currentPlayer, Game game) {
        int startIndex = 0;
        Boolean isAllHousesOwnedByThePlayerEmpty = true;
        if (Player.SYSTEM.equals(currentPlayer)) {
            startIndex = 7;
        }
        for (int house = startIndex; house < currentPlayer.houseIndex() && isAllHousesOwnedByThePlayerEmpty; house++)
            isAllHousesOwnedByThePlayerEmpty = isAllHousesOwnedByThePlayerEmpty && game.getKalahBoard().board[house] == 0;
        return isAllHousesOwnedByThePlayerEmpty;

    }

    public static void clearBoardAndResults(Game game) {
        int house = 0;
        for (house = 0; house < Player.USER.houseIndex(); house++) {
            game.getKalahBoard().board[Player.USER.houseIndex()] += game.getKalahBoard().board[house];
        }
        for (house = house + 1; house < Player.SYSTEM.houseIndex(); house++) {
            game.getKalahBoard().board[Player.SYSTEM.houseIndex()] += game.getKalahBoard().board[house];
        }
        if (game.getKalahBoard().board[Player.USER.houseIndex()] == game.getKalahBoard().board[Player.SYSTEM.houseIndex()]) {
            game.setGameStatus(Status.DRAW);
        } else {
            game.setWinner(game.getKalahBoard().board[Player.USER.houseIndex()] > game.getKalahBoard().board[Player.SYSTEM.houseIndex()] ? Player.USER : Player.SYSTEM);
        }
    }

    public static int getNextIndex(int houseIndex, Player opponent) {
        return opponent.houseIndex() == (houseIndex + 1) % 14 ? (houseIndex + 2) % 14 : (houseIndex + 1) % 14;
    }
}
