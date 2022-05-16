package com.demo.kalah.service;

import com.demo.kalah.exception.NoSuchGameExistsException;
import com.demo.kalah.dto.GameServiceRequest;
import com.demo.kalah.dto.GameServiceResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface KalahGameComponent {
    /**
     * component method to perform sow operation
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    public GameServiceResponse sowSeeds(@NonNull final GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException;

    /**
     * Component Method to create game
     *
     * @param gameServiceRequest
     * @return
     */
    public GameServiceResponse createGame(@NonNull final GameServiceRequest gameServiceRequest);

    /**
     * Component Method to get game by gameId
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    public GameServiceResponse getGameById(GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException;
}
