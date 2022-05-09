package com.demo.kalah.dto.models;

import com.demo.kalah.constants.Player;
import com.demo.kalah.constants.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = {})
public class Game {
    @NonNull private String gameId;
    @NonNull  private Board kalahBoard;
    private Turn turn;
    private Status gameStatus;
    private Player winner;
}
