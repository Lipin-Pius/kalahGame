package com.demo.kalah.controller;

import com.demo.kalah.Exception.NoSuchGameExistsException;
import com.demo.kalah.Exception.SowingException;
import com.demo.kalah.dto.GameServiceRequest;
import com.demo.kalah.dto.GameServiceResponse;
import com.demo.kalah.service.KalahGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * kalah backend end point contains handles following API calls
 * 1. /init -> create a kalah game
 * 2. /move -> make move in the game
 * 3. /game/{gameId} -> return the game corresponding to given gameId
 */
@RestController
@CrossOrigin("http://localhost:3000/")
public class KalahController {

    @Autowired
    private KalahGameService kalahGameService;

    /**
     * POST /init
     * expected payload: {}
     * response: {
     * gameId:String,
     * game:Game
     * }
     * used to create a game of kalah
     *
     * @param gameServiceRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/init")
    public GameServiceResponse initGame(@RequestBody GameServiceRequest gameServiceRequest) {
        return kalahGameService.createGame(gameServiceRequest);
    }


    /**
     * POST /move
     * expected payload: {
     * gameId: String,
     * houseId: Integer
     * }
     * response:{
     * gameId:String,
     * game:Game
     * }
     * Note: Designed for User-System Gameplay. User's move is not validated on the assumption that user can only choose from user's houses only.
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     * @throws SowingException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/move")
    public GameServiceResponse move(@RequestBody GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException, SowingException {
        return kalahGameService.move(gameServiceRequest);
    }


    /**
     * GET /game/{gameId}
     * response: {
     * gameId: String,
     * game:Game
     * }
     *
     * @param gameId
     * @return
     * @throws NoSuchGameExistsException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/game/{gameId}")
    public GameServiceResponse getGameById(@PathVariable final String gameId) throws NoSuchGameExistsException {
        GameServiceRequest gameServiceRequest = new GameServiceRequest();
        gameServiceRequest.setGameId(gameId);
        return kalahGameService.getGameById(gameServiceRequest);
    }


}
