package com.demo.kalah.service;

import com.demo.kalah.Exception.NoSuchGameExistsException;
import com.demo.kalah.Exception.SowingException;
import com.demo.kalah.dto.GameServiceRequest;
import com.demo.kalah.dto.GameServiceResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface KalahGameService {
    /**
     * Service to perform move
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     * @throws SowingException
     */
    public GameServiceResponse move(@NonNull final GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException, SowingException;

    /**
     * Service to create a game
     *
     * @param gameServiceRequest
     * @return
     */
    public GameServiceResponse createGame(@NonNull final GameServiceRequest gameServiceRequest);

    /**
     * Service to fetch a game by its ID
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    GameServiceResponse getGameById(GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException;
}
