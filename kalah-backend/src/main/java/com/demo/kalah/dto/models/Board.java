package com.demo.kalah.dto.models;


import com.demo.kalah.constants.Player;

public class Board {
    private static final Integer BOARD_SIZE = 14;
    private static final Integer SEEDS_PER_HOUSE = 4;

    public Integer[] board = new Integer[14];

    public Board() {
        for(int i=0;i<BOARD_SIZE;i++){
            if(i!= Player.USER.houseIndex() && i!= Player.SYSTEM.houseIndex()){
                board[i]=SEEDS_PER_HOUSE;
            }else{
                board[i]=0;
            }
        }
    }

}
