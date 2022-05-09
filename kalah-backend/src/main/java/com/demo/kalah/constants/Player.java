package com.demo.kalah.constants;

public enum Player {
    USER(6), SYSTEM(13);
    private Integer house;

    Player(int i) {
        this.house = i;
    }
    public Integer houseIndex(){
        return house;
    }
}
