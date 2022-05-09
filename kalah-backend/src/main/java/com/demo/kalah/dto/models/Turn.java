package com.demo.kalah.dto.models;

import com.demo.kalah.constants.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Turn {
    private final Player currentPlayer;
    private Integer lastHouseIndex;
}
