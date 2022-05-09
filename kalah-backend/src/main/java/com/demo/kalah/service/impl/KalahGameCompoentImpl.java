package com.demo.kalah.service.impl;

import com.demo.kalah.Exception.NoSuchGameExistsException;
import com.demo.kalah.constants.Player;
import com.demo.kalah.dto.GameServiceRequest;
import com.demo.kalah.dto.GameServiceResponse;
import com.demo.kalah.dto.models.Game;
import com.demo.kalah.dto.models.Turn;
import com.demo.kalah.repo.KalahGameRepository;
import com.demo.kalah.service.KalahGameComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KalahGameCompoentImpl implements KalahGameComponent {

    private KalahGameRepository kalahGameRepository;

    public KalahGameCompoentImpl(@Autowired final KalahGameRepository kalahGameRepository) {
        this.kalahGameRepository = kalahGameRepository;
    }

    /**
     * component method implementation to perform sow operation
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    @Override
    public GameServiceResponse sowSeeds(GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException {
        // fetch game state
        Game game = (kalahGameRepository.getGameById(gameServiceRequest)).getGame();
        // select the next player
        Player currentPlayer = gameServiceRequest.getTurn().getCurrentPlayer();
        // set the opponent
        Player opponent = currentPlayer.equals(Player.USER) ? Player.SYSTEM : Player.USER;
        // get the lastHouseIndex
        Integer lastHouseIndex = KalahGameHelper.getLastHouseIndex(gameServiceRequest.getTurn(), game);
        // Get the seed count in the selected house
        Integer seedCount = game.getKalahBoard().board[lastHouseIndex];
        // sow process starts
        int houseIndex = lastHouseIndex;
        // clear the house
        game.getKalahBoard().board[lastHouseIndex] = 0;
        while (seedCount > 0) {
            houseIndex = KalahGameHelper.getNextIndex(houseIndex, opponent);
            if (houseIndex != opponent.houseIndex()) {
                --seedCount;
                game.getKalahBoard().board[houseIndex] += 1;
            }

        }

        // check after sowing player landed on their own houses
        if (KalahGameHelper.isHouseOwnedByThePlayer(houseIndex, currentPlayer)) {
            if (KalahGameHelper.canPlayerSteal(houseIndex, game)) {
                KalahGameHelper.stealSeedsFrom(houseIndex, opponent, game);
                //switch the players
                game.setTurn(new Turn(opponent));
            }
            // player gets another turn
            game.setTurn(new Turn(currentPlayer));
        } else {
            // opponent's turn to play
            game.setTurn(new Turn(opponent));
        }

        // check whether game ended
        if (KalahGameHelper.isHousesOwnedByThePlayerEmpty(houseIndex, currentPlayer, game)) {
            KalahGameHelper.clearBoardAndResults(game);
        }
        // save the game
        //prepare GameService Request
        GameServiceRequest request = new GameServiceRequest();
        request.setGameId(game.getGameId());
        request.setGame(game);
        kalahGameRepository.saveKalahGameState(request);

        // create the response
        GameServiceResponse response = new GameServiceResponse(game.getGameId(), game);
        return response;

    }

    /**
     * Component Method implementation to create game
     *
     * @param gameServiceRequest
     * @return
     */
    @Override
    public GameServiceResponse createGame(GameServiceRequest gameServiceRequest) {
        return kalahGameRepository.createKalahGame(gameServiceRequest);
    }

    /**
     * Component Method implementation to get game by gameId
     *
     * @param gameServiceRequest
     * @return
     * @throws NoSuchGameExistsException
     */
    @Override
    public GameServiceResponse getGameById(GameServiceRequest gameServiceRequest) throws NoSuchGameExistsException {
        return kalahGameRepository.getGameById(gameServiceRequest);
    }
}
