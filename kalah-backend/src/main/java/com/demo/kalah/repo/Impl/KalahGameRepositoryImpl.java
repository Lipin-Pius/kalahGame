package com.demo.kalah.repo.Impl;

import com.demo.kalah.Exception.NoSuchGameExistsException;
import com.demo.kalah.dto.GameServiceRequest;
import com.demo.kalah.dto.GameServiceResponse;
import com.demo.kalah.dto.models.Board;
import com.demo.kalah.dto.models.Game;
import com.demo.kalah.repo.KalahGameRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * implementation of inmemory game data store.
 * Contains the implementation of KalahGameRepository interface
 */
@Component
public class KalahGameRepositoryImpl implements KalahGameRepository {
    private static final Map<String, Game> gameDataStore = new ConcurrentHashMap<>();

    /**
     * get Game instance for a given game Id
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    @Override
    public GameServiceResponse getGameById(@NonNull final GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException {
        GameServiceResponse response = new GameServiceResponse();
        if (null != gameServiceRequest.getGameId() && gameDataStore.containsKey(gameServiceRequest.getGameId())) {
            response.setGame(gameDataStore.get(gameServiceRequest.getGameId()));
        } else {
            throw new NoSuchGameExistsException(null != gameServiceRequest.getGameId() ? gameServiceRequest.getGameId() : "null");
        }
        return response;
    }

    /**
     * Create a new Game instance
     *
     * @param gameServiceRequest
     * @return
     */
    @Override
    public GameServiceResponse createKalahGame(@NonNull final GameServiceRequest gameServiceRequest) {
        String gameId = UUID.randomUUID().toString().replace("-", "");
        Game game = new Game(gameId, new Board());
        gameDataStore.put(gameId, game);
        GameServiceResponse response = new GameServiceResponse(gameId, game);
        return response;
    }

    /**
     * Save a game state
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    @Override
    public GameServiceResponse saveKalahGameState(@NonNull final GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException {
        if (null != gameServiceRequest.getGameId() && null != gameServiceRequest.getGame() && gameDataStore.containsKey(gameServiceRequest.getGameId())) {
            gameDataStore.put(gameServiceRequest.getGameId(), gameServiceRequest.getGame());
        } else {
            throw new NoSuchGameExistsException(null != gameServiceRequest.getGameId() ? gameServiceRequest.getGameId() : "null");
        }
        return new GameServiceResponse(gameServiceRequest.getGameId(), gameServiceRequest.getGame());
    }

    /**
     * Clear data store.
     */
    @Override
    public void purgeStore() {
        gameDataStore.clear();
        return;
    }
}
