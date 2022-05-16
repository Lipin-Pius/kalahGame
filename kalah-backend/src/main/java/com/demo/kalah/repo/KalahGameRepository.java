package com.demo.kalah.repo;

import com.demo.kalah.exception.NoSuchGameExistsException;
import com.demo.kalah.dto.GameServiceRequest;
import com.demo.kalah.dto.GameServiceResponse;
import org.springframework.stereotype.Repository;

/**
 * Game repository class, API for inmemory data store operations.
 * This implementation will be using an inmemory data store for saving the gameStates and games.
 * the data will be lost once the restarted.
 */
@Repository
public interface KalahGameRepository {
    /**
     * get Game instance for a given game Id
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    public GameServiceResponse getGameById(final GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException;

    /**
     * Create a new Game instance
     * @param gameServiceRequest
     * @return
     */
    public GameServiceResponse createKalahGame(final GameServiceRequest gameServiceRequest);

    /**
     * Save a game state
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    public GameServiceResponse saveKalahGameState(final GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException;

    /**
     * Clear data store.
     */
    public void purgeStore();
}
