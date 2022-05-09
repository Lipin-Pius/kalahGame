package com.demo.kalah.dto;

import com.demo.kalah.dto.models.Game;
import com.demo.kalah.dto.models.Turn;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameServiceRequest {
    private String gameId;
    private Integer houseId;
    private Turn turn;
    private Game game;

}
