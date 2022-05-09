package com.demo.kalah.dto;

import com.demo.kalah.dto.models.Game;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GameServiceResponse {
    private String gameId;
    private Game game;
}
