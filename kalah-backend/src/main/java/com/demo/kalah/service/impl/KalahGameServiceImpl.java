package com.demo.kalah.service.impl;

import com.demo.kalah.Exception.NoSuchGameExistsException;
import com.demo.kalah.Exception.SowingException;
import com.demo.kalah.constants.Player;
import com.demo.kalah.dto.GameServiceRequest;
import com.demo.kalah.dto.GameServiceResponse;
import com.demo.kalah.dto.models.Turn;
import com.demo.kalah.service.KalahGameComponent;
import com.demo.kalah.service.KalahGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KalahGameServiceImpl implements KalahGameService {

    private KalahGameComponent kalahGameComponent;

    public KalahGameServiceImpl(@Autowired final KalahGameComponent kalahGameComponent) {
        this.kalahGameComponent = kalahGameComponent;
    }

    @Override

    public GameServiceResponse move(GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException, SowingException {
        Turn currentTurn = null;
        if (null != gameServiceRequest.getTurn()) {
            currentTurn = new Turn(gameServiceRequest.getTurn().getCurrentPlayer(), gameServiceRequest.getTurn().getLastHouseIndex());
        } else {
            currentTurn = new Turn(Player.USER, gameServiceRequest.getHouseId());
        }

        GameServiceResponse response = null;
        do {
            gameServiceRequest.setTurn(currentTurn);
            response = kalahGameComponent.sowSeeds(gameServiceRequest);
            currentTurn = response.getGame().getTurn();

        } while (!Player.USER.equals(currentTurn.getCurrentPlayer()));
        if (null == response) {
            throw new SowingException(gameServiceRequest.getGameId() + currentTurn.getLastHouseIndex().toString());
        }
        return response;
    }

    @Override
    public GameServiceResponse createGame(GameServiceRequest gameServiceRequest) {
        return kalahGameComponent.createGame(gameServiceRequest);
    }

    @Override
    public GameServiceResponse getGameById(GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException {
        return kalahGameComponent.getGameById(gameServiceRequest);
    }


}
